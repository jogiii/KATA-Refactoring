package com.gildedrose;

class BackstagePassUpdater extends ItemUpdater {
    public BackstagePassUpdater(Item item) {
        super(item);
    }

    @Override
    public void update() {


        increaseQuality(1);
        if (item.sellIn <= 10) {
            increaseQuality(1);
        }
        if (item.sellIn <= 5) {
            increaseQuality(1);
        }
        decreaseSellIn(1);
        if (item.sellIn < 0) {

            // Quality drops to 0 after the concert
            item.quality = 0;
        }
    }
}
