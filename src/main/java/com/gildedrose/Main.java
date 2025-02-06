package com.gildedrose;

public class Main {
    public static void main(String[] args) {
        Item[] items = new Item[] {
            new Item("Aged Brie", 2, 0),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Normal Item", 10, 20),
            new Item("Conjured Mana Cake", 3, 6)
        };

        GildedRose app = new GildedRose(items);

        System.out.println("=== Initial Item States ===");
        printItems(items);

        // Simulate multiple days
        int days = 8;
        for (int day = 1; day <= days; day++) {
            System.out.println("\nDay " + day + ":");
            app.updateQuality();
            printItems(items);
        }
    }

    private static void printItems(Item[] items) {
        for (Item item : items) {
            System.out.println(item);
        }
    }
}
