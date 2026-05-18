package java.com.gildedrose.lazy;

import com.gildedrose.Item;

import java.com.gildedrose.GildedRose;
import java.com.gildedrose.lazy.implementations.AgedBrieItem;
import java.com.gildedrose.lazy.implementations.ConcertItem;
import java.com.gildedrose.lazy.implementations.ConjuredItem;
import java.com.gildedrose.lazy.implementations.SulfurasItem;
import java.util.Arrays;

/*  If the amount of items is substantially bigger than day-to-day item removing/adding,
    we can calculate item quality on the fly,
    instead of constantly decrementing every item quality, by using timestamps:
 */
public class LazyGildedRose extends GildedRose {

    public LazyGildedRose(Item[] items) {
        /*  Due to a specifics of current ruleset (every rule is unique for a specific type)
            simple switch operator with child classes was used instead of Builder pattern.

            Builder pattern with one "basic" class becomes favorable once the amount of types increases
            to the point where most of rules will apply to different types simultaneously:
        */
        this.items = Arrays.stream(items).parallel().map(item -> switch (item.name) {
            case AGED_BRIE -> new AgedBrieItem(item.name, item.sellIn, item.quality);
            case CONCERT -> new ConcertItem(item.name, item.sellIn, item.quality);
            case SULFURAS -> new SulfurasItem(item.name, item.sellIn, item.quality);
            case CONJURED -> new ConjuredItem(item.name, item.sellIn, item.quality);
            default -> new LazyCalculatedItem(item.name, item.sellIn, item.quality);
        }).toArray(Item[]::new);
    }
}
