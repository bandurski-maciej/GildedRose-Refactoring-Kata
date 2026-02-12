package com.gildedrose;

public class NormalItemStrategy implements ItemUpdateStrategy {

    @Override
    public void updateItem(Item item) {
        if (item.quality > 0) item.quality--;

        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0 && item.quality > 0) item.quality--;
    }

}
