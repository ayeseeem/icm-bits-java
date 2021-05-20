package org.ayeseeem.say.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Helpers and extensions for {@link java.util.List}.
 */
public class ListSupport {

    /**
     * Creates an unmodifiable {@link List} from the members.
     * <p>
     * This is just a wrapper for {@link #unmodifiableListOf(Object...)}, which
     * you can use if you want to make it clear that the list is unmodifiable.
     * If you want a modifiable list, use {@link #modifiableListOf(Object...)}.
     * <p>
     * This was inspired by the useful {@link Arrays#asList(Object...)}, but its
     * name reflects what is being produced, not what it is being produced from
     * ("list of things", rather than "array as a list").
     *
     * @param <T>
     *            member type
     * @param members
     *            the members of the list
     * @return a new, unmodifiable {@code List}
     *
     * @see #unmodifiableListOf(Object...)
     * @see SetSupport#setOf(Object...)
     */
    @SafeVarargs
    public static <T> List<T> listOf(T... members) {
        return unmodifiableListOf(members);
    }

    /**
     * Creates a modifiable {@link List} from the members.
     * <p>
     * This was inspired by the useful {@link Arrays#asList(Object...)}, but its
     * name reflects what is being produced, not what it is being produced from
     * ("list of things", rather than "array as a list"), and the list it produces
     * is modifiable (whereas the list produced by {@code Arrays#asList()} is
     * unmodifiable).
     *
     * @param <T>
     *            member type
     * @param members
     *            the members of the list
     * @return a new, modifiable {@code List}
     *
     * @see #unmodifiableListOf(Object...)
     */
    @SafeVarargs
    public static <T> List<T> modifiableListOf(T... members) {
        return new ArrayList<>(Arrays.asList(members));
    }

    /**
     * Creates an unmodifiable {@link List} from the members.
     * <p>
     * This was inspired by the useful {@link Arrays#asList(Object...)}, but its
     * name reflects what is being produced, not what it is being produced from
     * ("list of things", rather than "array as a list"), and makes explicit
     * that the list it produces is unmodifiable (whereas
     * {@code Arrays#asList()} does not make it clear).
     * <p>
     * This is like {@link Collections#unmodifiableList(List)}, but it takes
     * varargs, not a list.
     *
     * @param <T>
     *            member type
     * @param members
     *            the members of the list
     * @return a new, unmodifiable {@code List}
     *
     * @see #modifiableListOf(Object...)
     */
    @SafeVarargs
    public static <T> List<T> unmodifiableListOf(T... members) {
        return Arrays.asList(members);
    }

}
