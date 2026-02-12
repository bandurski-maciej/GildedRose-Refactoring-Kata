package com.gildedrose;

public class ItemStrategyFactory {

    public static ItemUpdateStrategy getStrategy(Item item) {
        switch (item.name) {
            case "Aged Brie":
                return new AgedBrieStrategy();
            case "Sulfuras, Hand of Ragnaros":
                return new SulfurasStrategy();
            case "Backstage passes to a TAFKAL80ETC concert":
                return new BackstagePassesStrategy();
            default:
                return new NormalItemStrategy();
        }
    }

}
