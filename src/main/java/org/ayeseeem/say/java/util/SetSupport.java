package org.ayeseeem.say.java.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Helpers and extensions for {@link java.util.Set}.
 */
public class SetSupport {

    /**
     * Creates a modifiable {@link Set} from the members, removing any duplicates.
     * <p>
     * This was inspired by the useful {@link Arrays#asList(Object...)}, but its
     * name reflects what is being produced, not what it is being produced from
     * ("set of things", rather than "array as a set"), and the set it produces is
     * modifiable (whereas the list produced by {@code Arrays#asList()} is
     * unmodifiable).
     *
     * @param <T>
     *            member type
     * @param members
     *            the members of the set
     * @return a new, modifiable {@code Set}
     *
     * @see ListSupport#listOf(Object...)
     */
    @SafeVarargs
    public static <T> Set<T> setOf(T... members) {
        Set<T> set = new HashSet<>();
        set.addAll(Arrays.asList(members));
        return set;
    }

}
