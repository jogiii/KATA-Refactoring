package com.gildedrose;

class AgedBrieUpdater extends ItemUpdater {
    public AgedBrieUpdater(Item item) {
        super(item);
    }

    @Override
    public void update() {
        //increasing quality instead of decreasing
        increaseQuality(1);
        decreaseSellIn(1);
        if (item.sellIn < 0) {
            //"Aged Brie" actually increases in Quality the older it gets, so increasing it twice after sellin date
            increaseQuality(1);
        }
    }
}
