package java.com.gildedrose;

import java.util.Date;
import com.gildedrose.Item;

public abstract class GildedRose {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED = "Conjured";
    public static final int MIN_QUALITY = 0;
    public static final int MAX_QUALITY = 50;

    protected Item[] items;

    // Base rules, applicable to every "Gilded Rose" implementation:
    public static int adjustItemQuality(String name, int daysPassed) {
        switch(name) {
            case AGED_BRIE: return daysPassed;
            case CONCERT: return daysPassed;
            case SULFURAS: return 0;
            case CONJURED: return -daysPassed * 2;
            default: return -daysPassed;
        }
    }

    protected void normalizeQuality(int quality) {
        if (quality < MIN_QUALITY) {
            quality = MIN_QUALITY;
        }
        if (quality > MAX_QUALITY) {
            quality = MAX_QUALITY;
        }
    }
}
