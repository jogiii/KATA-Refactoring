package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;


class GildedRoseTest {



    private Item[] items;
    private GildedRose gildedRose;

    @BeforeEach
    void setUp() {
        items = new Item[]{
            new Item("Aged Brie", 10, 20),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Conjured", 5, 25),
            new Item("Normal Item", 5, 10),
            new Item("NewItem",10,20) };
        gildedRose = new GildedRose(items);
    }

    @Test
    void testAgedBrieIncreasesInQuality() {
        gildedRose.updateQuality();
        assertEquals(21, items[0].quality);
        assertEquals(9, items[0].sellIn);
    }

    @Test
    void testSulfurasNeverChanges() {
        gildedRose.updateQuality();
        assertEquals(80, items[1].quality);
        assertEquals(0, items[1].sellIn);
    }

    @Test
    void testBackstagePassIncreasesQualityNormally() {
        gildedRose.updateQuality();
        assertEquals(21, items[2].quality);
        assertEquals(14, items[2].sellIn);
    }

    @Test
    void testBackstagePassIncreasesQualityBy2When10DaysOrLess() {
        items[2].sellIn = 10;
        gildedRose.updateQuality();
        assertEquals(22, items[2].quality);
    }

    @Test
    void testBackstagePassIncreasesQualityBy3When5DaysOrLess() {
        items[2].sellIn = 5;
        gildedRose.updateQuality();
        assertEquals(23, items[2].quality);
    }

    @Test
    void testBackstagePassDropsQualityToZeroAfterConcert() {
        items[2].sellIn = 0;
        gildedRose.updateQuality();
        assertEquals(0, items[2].quality);
    }

    @Test
    void testNormalItemDegradesNormally() {
        gildedRose.updateQuality();
        assertEquals(9, items[4].quality);
        assertEquals(4, items[4].sellIn);
    }

    @Test
    void testQualityNeverNegative() {
        items[4].quality = 0;
        gildedRose.updateQuality();
        assertEquals(0, items[4].quality);
    }

    @Test
    void testQualityNeverExceedsFiftyForAgedBrie() {
        items[0].quality = 50;
        gildedRose.updateQuality();
        assertEquals(50, items[0].quality);
    }

    @Test
    void testBackstagePassQualityNeverExceedsFifty() {
        items[2].sellIn = 5;
        items[2].quality = 49;
        gildedRose.updateQuality();
        assertEquals(50, items[2].quality);
    }

    @Test
    void testAgedBrieIncreasesTwiceAsFastAfterSellIn() {
        items[0].sellIn = 0;
        gildedRose.updateQuality();
        assertEquals(22, items[0].quality);
    }

    @Test
    void testConjuredItemDegradesTwiceAsFastAfterSellInDate() {
        items[3].sellIn = 0;
        gildedRose.updateQuality();
        assertEquals(21, items[3].quality);
    }

    @Test
    void testConjuredItemDegradesTwiceAsFast() {
        gildedRose.updateQuality();
        assertEquals(23, items[3].quality);
        assertEquals(4, items[3].sellIn);
    }

    @Test
    void testNewItemIncreaseTwiceAsFast() {
        gildedRose.updateQuality();
        assertEquals(22, items[5].quality);
        assertEquals(9, items[5].sellIn);
    }

    @Test
    void testNewItemIncreaseTwiceAsFastAfterSellInDate() {
        items[5].sellIn = 0;
        gildedRose.updateQuality();
        assertEquals(24, items[5].quality);
    }




}
