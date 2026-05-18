package java.com.gildedrose.lazy;

import java.com.gildedrose.lazy.implementations.AgedBrieItem;
import java.com.gildedrose.lazy.implementations.ConcertItem;
import java.com.gildedrose.lazy.implementations.ConjuredItem;
import java.com.gildedrose.lazy.implementations.SulfurasItem;

import static java.com.gildedrose.GildedRose.*;

public sealed class LazyCalculatedItem extends com.gildedrose.Item permits AgedBrieItem, ConcertItem, ConjuredItem, SulfurasItem {

    private static final Long startUnixTime = System.currentTimeMillis() / 1000;

    public int sellInDaysSinceStart;

    public LazyCalculatedItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
        this.sellInDaysSinceStart = sellIn + getDaysSinceStart();
    }

    //Adjust item quality as if item was placed in the store from day one:
    protected static int getDaysSinceStart() {
        var timePassed = System.currentTimeMillis() / 1000 - startUnixTime;
        return Math.toIntExact(timePassed) / 24 / 3600;
    }

    public int calculateQuality() {
        var daysInStockLeft = sellInDaysSinceStart - getDaysSinceStart();
        var normalAmortizationDays = sellIn - daysInStockLeft;
        quality -= adjustItemQuality(this.name, normalAmortizationDays);
        if (daysInStockLeft < 0) {
            handleSellInPassed(daysInStockLeft);
        }
        return quality - sellInDaysSinceStart;
    }

    public void handleSellInPassed(int daysInStockLeft) {
        this.quality -= adjustItemQuality(this.name, daysInStockLeft);
    }
}
