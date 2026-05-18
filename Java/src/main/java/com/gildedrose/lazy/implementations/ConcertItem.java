package java.com.gildedrose.lazy.implementations;

import java.com.gildedrose.interfaces.ItemWithApproachableSellIn;
import java.com.gildedrose.interfaces.ItemWithSellIn;
import java.com.gildedrose.lazy.LazyCalculatedItem;

import static java.com.gildedrose.GildedRose.adjustItemQuality;

public final class ConcertItem extends LazyCalculatedItem implements ItemWithSellIn, ItemWithApproachableSellIn {

    public static final int CONCERT_HOTTEST_DAYS = 6;
    public static final int CONCERT_HOT_DAYS = 11;

    public ConcertItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public int calculateQuality() {
        var daysPassedSinceStart = getDaysSinceStart();
        adjustItemQuality(name, daysPassedSinceStart);
        var daysInStockLeft = daysPassedSinceStart - sellInDaysSinceStart;
        if(daysInStockLeft < 0) {
            handleSellInPassed(daysInStockLeft);
        } else {
            handleSellInApproaches(daysInStockLeft);
        }
        return quality;
    }

    //Could be implemented with Builder pattern, if more complex rules apply:
    @Override
    public void handleSellInApproaches(int daysInStockLeft) {
        this.quality += adjustItemQuality(name, Math.max(CONCERT_HOT_DAYS - daysInStockLeft, 0));
        this.quality += adjustItemQuality(name, Math.max(CONCERT_HOTTEST_DAYS - daysInStockLeft, 0));
    }

    @Override
    public void handleSellInPassed(int daysInStockLeft) {
        this.quality = 0;
    }
}
