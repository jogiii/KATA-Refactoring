package com.gildedrose;

public class NewItemUpdater extends ItemUpdater {
    public NewItemUpdater(Item item) {
        super(item);
    }

    @Override
    public void update() {

        increaseQuality(2);

         // increase twice as fast
        decreaseSellIn(1);
        if (item.sellIn < 0) {
            increaseQuality(2);

        }

    }
}
