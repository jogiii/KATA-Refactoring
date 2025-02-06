# Gilded Rose starting position in Java

## Overview
This project is a Java implementation of the Gilded Rose kata, featuring a flexible design that allows dynamic item updater registration using a YAML configuration file.


## Design

The application follows an extensible design using the Strategy pattern, where different types of items are updated by their respective `ItemUpdater` implementations. Item updater class mapping is configured in the `updaterRegistry.yml` file.

### Design Diagram

```plaintext
+----------------+
|  GildedRose    |
+----------------+
         |
         v
+----------------+
| ItemUpdater    |
+----------------+
         ^
         |
+---------------------+
| Concrete Updaters   |
| (e.g., AgedBrie)    |
+---------------------+
```

## Configuration
The `updaterRegistry.yml` file maps item name prefixes to corresponding updater classes:

```yaml
Aged: AgedBrieUpdater
Sulfuras: SulfurasUpdater
Backstage: BackstagePassUpdater
Conjured: ConjuredItemUpdater
New: NewItemUpdater
```

## Code Structure

- `GildedRose.java`: Core class managing item updates.
- `Item.java`: Represents an inventory item.
- `ItemUpdater.java`: Abstract class defining update behavior.
- Concrete Updaters (e.g., `AgedBrieUpdater.java`, `SulfurasUpdater.java`) handle specific item types.

## How It Works
1. The registry is initialized by reading `updaterRegistry.yml`.
2. Items are updated using their corresponding updater classes.
3. New updaters can be added simply by updating the YAML file and creating the corresponding class.

## Adding New Item Updaters
1. Define a new class extending `ItemUpdater`.
2. Update `updaterRegistry.yml` with the item prefix and new updater class name.
3. Run the program; the new updater will be automatically registered.

