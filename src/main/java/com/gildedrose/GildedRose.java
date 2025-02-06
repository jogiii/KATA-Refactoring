package com.gildedrose;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

class GildedRose {

    private Item[] items;

    //using generics here any sub class of ItemUpdate
    private Map<String, Class<? extends ItemUpdater>> updaterRegistry;

    public GildedRose(Item[] items) {
        this.items = validateItems(items);
        initializeRegistry();
    }

    private void initializeRegistry() {
        updaterRegistry = new HashMap<>();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("updaterRegistry.yml")) {
            if (input == null) {
                throw new RuntimeException("Unable to find updaterRegistry.yml");
            }

            Yaml yaml = new Yaml();
            Map<String, String> registryMap = yaml.load(input);
            System.out.println(registryMap);
            for (Map.Entry<String, String> entry : registryMap.entrySet()) {
                String prefix = entry.getKey();
                String updaterClassName = entry.getValue();
                try {
                    Class<?> updaterClass = Class.forName("com.gildedrose." + updaterClassName);
                    // Ensure it is a subclass of ItemUpdater
                    updaterRegistry.put(prefix, updaterClass.asSubclass(ItemUpdater.class));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("Updater class not found: " + updaterClassName, e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading updater registry from YAML", e);
        }
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemUpdater updater = getUpdater(item);
            updater.update();
        }
    }

    private ItemUpdater getUpdater(Item item) {
        // Find the correct updater based on name prefix
        Class<? extends ItemUpdater> updaterClass = null;

        for (Map.Entry<String, Class<? extends ItemUpdater>> entry : updaterRegistry.entrySet()) {
            if (item.name.startsWith(entry.getKey())) {
                updaterClass = entry.getValue();
                break;  // Stop as soon as we find a match
            }
        }

        // Default to NormalItemUpdater if no match is found
        if (updaterClass == null) {
            updaterClass = NormalItemUpdater.class;
        }

        try {
            return updaterClass.getDeclaredConstructor(Item.class).newInstance(item);
        } catch (Exception e) {
            throw new RuntimeException("Error creating ItemUpdater instance", e);
        }
    }


    private Item[] validateItems(Item[] items) {
        if (items == null || items.length == 0) {
            throw new IllegalArgumentException("Items list cannot be null or empty");
        }
        for (Item item : items) {
            validateItem(item);
        }
        return items;
    }

    private void validateItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (item.name == null || item.name.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be null or empty");
        }
        if (!Pattern.matches("[a-zA-Z0-9, ]+", item.name)) {
            throw new IllegalArgumentException("Item name contains invalid characters");
        }
    }


}
