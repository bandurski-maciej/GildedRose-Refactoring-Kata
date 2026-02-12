package com.gildedrose;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

    @Test
    void normalItem_decreasesSellInAndQualityByOneBeforeExpiration() {
        Item[] items = { new Item("Elixir of the Mongoose", 5, 7) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertAll(
            () -> assertEquals(4, items[0].sellIn),
            () -> assertEquals(6, items[0].quality)
        );
    }

    @Test
    void normalItem_degradesTwiceAsFastAfterExpiration() {
        Item[] items = { new Item("Elixir of the Mongoose", 0, 7) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertAll(
            () -> assertEquals(-1, items[0].sellIn),
            () -> assertEquals(5, items[0].quality)
        );
    }

    @Test
    void agedBrie_increasesInQuality() {
        Item[] items = { new Item("Aged Brie", 2, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertAll(
            () -> assertEquals(1, items[0].sellIn),
            () -> assertEquals(1, items[0].quality)
        );
    }

    @Test
    void agedBrie_qualityNeverExceeds50() {
        Item[] items = { new Item("Aged Brie", 2, 50) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, items[0].quality);
    }

    @Test
    void sulfuras_neverChanges() {
        Item[] items = { new Item("Sulfuras, Hand of Ragnaros", 0, 80) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertAll(
            () -> assertEquals(0, items[0].sellIn),
            () -> assertEquals(80, items[0].quality)
        );
    }

    @Test
    void backstagePasses_increaseByTwoWhen10DaysOrLess() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(22, items[0].quality);
    }

    @Test
    void backstagePasses_increaseByThreeWhen5DaysOrLess() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(23, items[0].quality);
    }

    @Test
    void backstagePasses_dropToZeroAfterConcert() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, items[0].quality);
    }

    @Test
    void quality_neverNegative() {
        Item[] items = { new Item("Elixir of the Mongoose", 5, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, items[0].quality);
    }
}

