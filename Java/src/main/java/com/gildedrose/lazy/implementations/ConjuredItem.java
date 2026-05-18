package java.com.gildedrose.lazy.implementations;

import java.com.gildedrose.interfaces.ItemWithSellIn;
import java.com.gildedrose.lazy.LazyCalculatedItem;

import static java.com.gildedrose.GildedRose.adjustItemQuality;

public final class ConjuredItem extends LazyCalculatedItem implements ItemWithSellIn {

    public ConjuredItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void handleSellInPassed(int daysInStockLeft) {
        this.quality -= adjustItemQuality(this.name, daysInStockLeft);
    }
}
