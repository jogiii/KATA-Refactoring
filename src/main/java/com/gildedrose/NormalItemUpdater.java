package com.gildedrose;

class NormalItemUpdater extends ItemUpdater {
    public NormalItemUpdater(Item item) {
        super(item);
    }

    @Override
    public void update() {
        decreaseQuality(1);
        decreaseSellIn(1);
        if (item.sellIn < 0) {
            decreaseQuality(1);
        }
    }
}
