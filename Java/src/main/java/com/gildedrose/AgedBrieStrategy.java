package com.gildedrose;

class AgedBrieStrategy implements ItemUpdateStrategy {

    @Override
    public void updateItem(Item item) {
        if (item.quality < 50) item.quality++;
        item.sellIn--;
        if (item.sellIn < 0 && item.quality < 50) item.quality++;
    }

}
