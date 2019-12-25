package org.ayeseeem.say.java.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Helpers and extensions for {@link java.util.Map}.
 */
public class MapSupport {

    /**
     * Creates an empty, modifiable {@link SortedMap} where sorting is by key.
     * <p>
     * This is a descriptive convenience method. It creates a {@link TreeMap}, but
     * the method describes <em>why</em> this type of map is being chosen. The
     * implementation is an irrelevant detail, it's the behaviour that is important.
     *
     * @param <KeyT>
     *            key type
     * @param <ValueT>
     *            value type
     * @return a new, modifiable {@code SortedMap}
     */
    public static <KeyT, ValueT> SortedMap<KeyT, ValueT> keySortedMap() {
        return new TreeMap<>();
    }

    /**
     * Creates an empty, modifiable {@code Map} where iteration order is insertion
     * order.
     * <p>
     * This is a descriptive convenience method. It creates a {@link LinkedHashMap},
     * but the method describes <em>why</em> this type of map is being chosen. The
     * implementation is an irrelevant detail, it's the behaviour that is important.
     *
     * @param <KeyT>
     *            key type
     * @param <ValueT>
     *            value type
     * @return a new, modifiable {@code Map}
     */
    public static <KeyT, ValueT> Map<KeyT, ValueT> insertionOrderedMap() {
        return new LinkedHashMap<>();
    }

}
