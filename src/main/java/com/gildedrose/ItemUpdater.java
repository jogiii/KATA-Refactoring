package com.gildedrose;

abstract class ItemUpdater {
    protected Item item;

    public ItemUpdater(Item item) {
        this.item = item;
    }

    public abstract void update();

    protected void decreaseSellIn(int num) {

        item.sellIn = item.sellIn - num;
    }

    protected void increaseQuality(int num) {
        if (item.quality < 50) {
            item.quality = item.quality+num;
        }
    }

    protected void decreaseQuality(int num) {
        if (item.quality > 0) {
            item.quality = item.quality - num;
        }
    }
}
