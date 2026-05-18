package java.com.gildedrose.lazy.implementations;

import java.com.gildedrose.lazy.LazyCalculatedItem;

public final class SulfurasItem extends LazyCalculatedItem {

    public SulfurasItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public int calculateQuality() {
        return quality;
    }
}
