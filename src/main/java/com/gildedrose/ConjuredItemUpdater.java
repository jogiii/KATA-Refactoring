package com.gildedrose;

class ConjuredItemUpdater extends ItemUpdater {
    public ConjuredItemUpdater(Item item) {
        super(item);
    }

    @Override
    public void update() {
        decreaseQuality(2);
        // Degrades twice as fast
        decreaseSellIn(1);
        if (item.sellIn < 0) {
            decreaseQuality(2);

        }
    }
}
