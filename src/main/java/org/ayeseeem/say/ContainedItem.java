package org.ayeseeem.say;

import java.util.Arrays;
import java.util.Collection;

/**
 * An item that might be contained in some group of elements. Designed to allow
 * more expressive {@code if} statements - see {@link #the(Object)}.
 *
 * @param <T>
 *            the item type
 */
public class ContainedItem<T> {
    private final T item;

    private ContainedItem(T item) {
        this.item = item;
    }

    /**
     * Creates a contained item. This method provides a way to write more expressive
     * checks, for example:
     *
     * <pre>
     * if (the(month).isIn(myFavouriteMonths) {
     *     // ...
     * }
     *
     * if (the(month).isOneOf(SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER) {
     *     // it's name derives from its Latin ordinal in the Roman calendar
     * }
     * </pre>
     *
     * @param item
     *            the item to check
     * @param <T>
     *            the item type
     * @return a new {@code ContainedItem}
     */
    public static <T> ContainedItem<T> the(T item) {
        return new ContainedItem<>(item);
    }

    /**
     * Checks whether the item is contained in a Java {@link Collection}.
     *
     * @param collection
     *            the collection to check
     * @return true if this item is in the collection
     */
    public boolean isIn(Collection<T> collection) {
        return collection.contains(item);
    }

    /**
     * Checks whether the item is contained in an array.
     *
     * @param array
     *            an array of Objects, not primitive types
     * @return true if this item is in the array
     */
    public boolean isIn(T[] array) {
        return isIn(Arrays.asList(array));
    }

    /**
     * Checks whether the item is contained in some items.
     *
     * @param items
     *            the items to check
     * @return true if this item is one of the items
     */
    @SafeVarargs
    public final boolean isOneOf(T... items) {
        return isIn(Arrays.asList(items));
    }

}
