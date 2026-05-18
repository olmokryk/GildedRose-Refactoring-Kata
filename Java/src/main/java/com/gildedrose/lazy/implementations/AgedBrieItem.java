package java.com.gildedrose.lazy.implementations;

import java.com.gildedrose.interfaces.ItemWithSellIn;
import java.com.gildedrose.lazy.LazyCalculatedItem;

public final class AgedBrieItem extends LazyCalculatedItem implements ItemWithSellIn {

    //"Aged Brie" behaves in the opposite way to normal item, including "after sell-in" period:
    public AgedBrieItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }
}
