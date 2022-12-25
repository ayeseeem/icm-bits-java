package org.ayeseeem.say.java.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Helpers and extensions for {@link java.util.Set}.
 */
public class SetSupport {

    /**
     * Creates an unmodifiable {@link Set} from the members, removing any duplicates.
     * <p>
     * This is just a wrapper for {@link #unmodifiableSetOf(Object...)}, which
     * you can use if you want to make it clear that the set is unmodifiable.
     * If you want a modifiable set, use {@link #modifiableSetOf(Object...)}.
     * <p>
     * This was inspired by {@link ListSupport#listOf(Object...)}.
     *
     * @param <T>
     *            member type
     * @param members
     *            the members of the set
     * @return a new, unmodifiable {@code Set}
     *
     * @see #unmodifiableSetOf(Object...)
     * @see #modifiableSetOf(Object...)
     * @see #emptySet()
     * @see ListSupport#listOf(Object...)
     */
    @SafeVarargs
    public static <T> Set<T> setOf(T... members) {
        return unmodifiableSetOf(members);
    }

    /**
     * Creates an empty set in a more expressive way than using {@code setOf()}
     * ({@link #setOf(Object...)} with no arguments). If modifiability is
     * significant, use {@link #initiallyEmptySet()} or {@link #alwaysEmptySet()}.
     *
     * @param <T>
     *            member type
     * @return an empty set the equivalent of using {@code setOf()}
     *
     * @see #setOf(Object...)
     */
    public static <T> Set<T> emptySet() {
        return setOf();
    }

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
     * @see #unmodifiableSetOf(Object...)
     * @see #initiallyEmptySet()
     */
    @SafeVarargs
    public static <T> Set<T> modifiableSetOf(T... members) {
        Set<T> set = new HashSet<>();
        set.addAll(Arrays.asList(members));
        return set;
    }

    /**
     * Creates an empty, modifiable {@link Set} in a more expressive way than using
     * {@code modifiableSetOf()} ({@link #modifiableSetOf(Object...)} with no
     * arguments).
     *
     * @param <T>
     *            member type
     * @return a new, modifiable {@code Set}
     *
     * @see #modifiableSetOf(Object...)
     * @see #alwaysEmptySet()
     */
    public static <T> Set<T> initiallyEmptySet() {
        return modifiableSetOf();
    }

    /**
     * Creates an unmodifiable {@link Set} from the members, removing any duplicates.
     * <p>
     * This was inspired by the useful {@link Arrays#asList(Object...)}, but its
     * name reflects what is being produced, not what it is being produced from
     * ("set of things", rather than "array as a set"), and makes explicit that
     * the list it produces is unmodifiable (whereas
     * {@code Arrays#asList()} does not make it clear).
     * <p>
     * This is like {@link Collections#unmodifiableSet(Set)}, but it takes
     * varargs, not a set. Also, it removes any duplicates rather than throwing
     * an exception.
     *
     * @param <T>
     *            member type
     * @param members
     *            the members of the set
     * @return a new, unmodifiable {@code Set}
     *
     * @see #modifiableSetOf(Object...)
     * @see #alwaysEmptySet()
     */
    @SafeVarargs
    public static <T> Set<T> unmodifiableSetOf(T... members) {
        Set<T> set = new HashSet<>();
        set.addAll(Arrays.asList(members));
        return Collections.unmodifiableSet(set);
    }

    /**
     * Creates an empty, unmodifiable {@link Set} in a more expressive way than
     * using {@code unmodifiableSetOf()} ({@link #unmodifiableSetOf(Object...)}
     * with no arguments).
     *
     * @param <T>
     *            member type
     * @return a new, unmodifiable {@code Set}
     *
     * @see #unmodifiableSetOf(Object...)
     * @see #initiallyEmptySet()
     */
    public static <T> Set<T> alwaysEmptySet() {
        return unmodifiableSetOf();
    }

    /**
     * Creates an empty, modifiable {@code Set} where iteration order is insertion
     * order.
     * <p>
     * This is a descriptive convenience method. It creates a {@link LinkedHashSet},
     * but the method describes <em>why</em> this type of set is being chosen. The
     * implementation is an irrelevant detail, it's the behaviour that is important.
     *
     * @param <T>
     *            member type
     * @return a new, modifiable {@code Set}
     */
    public static <T> Set<T> insertionOrderedSet() {
        return new LinkedHashSet<>();
    }

}
