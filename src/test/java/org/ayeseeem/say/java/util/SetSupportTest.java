package org.ayeseeem.say.java.util;

import static org.ayeseeem.say.java.util.DummyValue.dummy;
import static org.ayeseeem.say.java.util.ListSupport.listOf;
import static org.ayeseeem.say.java.util.SetSupport.alwaysEmptySet;
import static org.ayeseeem.say.java.util.SetSupport.emptySet;
import static org.ayeseeem.say.java.util.SetSupport.initiallyEmptySet;
import static org.ayeseeem.say.java.util.SetSupport.modifiableSetOf;
import static org.ayeseeem.say.java.util.SetSupport.setOf;
import static org.ayeseeem.say.java.util.SetSupport.unmodifiableSetOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;

import java.util.List;
import java.util.Set;

import org.ayeseeem.test.Characterization;
import org.junit.Test;

public class SetSupportTest {

    @Test
    public void testSetOf() {
        assertThat(setOf("a", "b", "c"), containsInAnyOrder("a", "b", "c"));
    }

    @Test
    public void testSetOf_ArbitraryType() {
        assertThat(setOf(dummy(1), dummy(2), dummy(3)),
                containsInAnyOrder(dummy(1), dummy(2), dummy(3)));
    }

    @Test
    public void testSetOf_AllowsMixedTypes() {
        assertThat(setOf("a", dummy(1)), containsInAnyOrder("a", dummy(1)));
    }

    @Test
    public void testSetOf_RemovesDuplicates() {
        assertThat(setOf("a", "b", "b", "z"), containsInAnyOrder("a", "b", "z"));
    }

    @Test
    public void testSetOf_RemovesDuplicates_ArbitraryType() {
        assertThat(setOf(dummy(1), dummy(2), dummy(2), dummy(10)),
                containsInAnyOrder(dummy(1), dummy(2), dummy(10)));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSetOf_CreatesStructurallyUnmodifiableSet_CannotRemoveElement() {
        Set<String> set = setOf("a", "b", "c");

        set.remove("b");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSetOf_CreatesStructurallyUnmodifiableSet_CannotAddElement() {
        Set<String> set = setOf("a", "b", "c");

        set.add("d");
    }

    @Test
    public void testSetOf_Modifiability_Matches_ListOf() {
        Set<String> defaultSet = setOf();
        try {
            defaultSet.add("a");
        } catch (UnsupportedOperationException possible) {
            // might be unmodifiable
        }

        List<String> defaultList = listOf();
        try {
            defaultList.add("a");
        } catch (UnsupportedOperationException possible) {
            // might be unmodifiable
        }

        assertThat(defaultSet.size(), is(defaultList.size()));
    }

    @Test
    public void testEmptySet() {
        assertThat(emptySet(), is(empty()));
    }

    @Test
    public void testEmptySet_ArbitraryType() {
        Set<DummyValue> emptySet = emptySet();
        assertThat(emptySet, is(empty()));
    }

   @Test
    public void testEmptySet_Modifiability_Matches_Simple_SetOf() {
        Set<String> defaultSet= setOf();
        try {
            defaultSet.add("a");
        } catch (UnsupportedOperationException possible) {
            // might be unmodifiable
        }

        Set<String> emptySet = emptySet();
        try {
            emptySet.add("a");
        } catch (UnsupportedOperationException possible) {
            // might be unmodifiable
        }

        assertThat(defaultSet, is(emptySet));
    }

    @Test
    public void testModifiableSetOf() {
        assertThat(modifiableSetOf("a", "b", "c"), containsInAnyOrder("a", "b", "c"));
    }

    @Test
    public void testModifiableSetOf_ArbitraryType() {
        assertThat(modifiableSetOf(dummy(1), dummy(2), dummy(3)),
                containsInAnyOrder(dummy(1), dummy(2), dummy(3)));
    }

    @Test
    public void testModifiableSetOf_AllowsMixedTypes() {
        assertThat(modifiableSetOf("a", dummy(1)), containsInAnyOrder("a", dummy(1)));
    }

    @Test
    public void testModifiableSetOf_RemovesDuplicates() {
        assertThat(modifiableSetOf("a", "b", "b", "z"), containsInAnyOrder("a", "b", "z"));
    }

    @Test
    public void testModifiableSetOf_RemovesDuplicates_ArbitraryType() {
        assertThat(modifiableSetOf(dummy(1), dummy(2), dummy(2), dummy(10)),
                containsInAnyOrder(dummy(1), dummy(2), dummy(10)));
    }

    @Test
    public void testModifiableSetOf_CreatesStructurallyModifiableSet() {
        Set<String> set = modifiableSetOf("a", "b", "c");
        set.remove("b");
        assertThat(set, containsInAnyOrder("a", "c"));

        set.add("d");
        set.add("e");
        assertThat(set, containsInAnyOrder("a", "c", "d", "e"));
    }

    @Test
    public void testInitiallyEmptySet() {
        assertThat(initiallyEmptySet(), is(empty()));
    }

    @Test
    public void testInitiallyEmptySet_CreatesStructurallyModifiableSet() {
        Set<String> set = initiallyEmptySet();

        set.add("a");
        set.add("b");
        assertThat(set, contains("a", "b"));
    }

    @Test
    public void testInitiallyEmptySet_ArbitraryType() {
        Set<DummyValue> set = initiallyEmptySet();

        set.add(dummy(1));
        set.add(dummy(2));
        assertThat(set, contains(dummy(1), dummy(2)));
    }

    @Test
    public void testUnmodifiableSetOf() {
        assertThat(unmodifiableSetOf("a", "b", "c"), containsInAnyOrder("a", "b", "c"));
    }

    @Test
    public void testUnmodifiableSetOf_ArbitraryType() {
        assertThat(unmodifiableSetOf(dummy(1), dummy(2), dummy(3)),
                containsInAnyOrder(dummy(1), dummy(2), dummy(3)));
    }

    @Test
    public void testUnmodifiableSetOf_AllowsMixedTypes() {
        assertThat(unmodifiableSetOf("a", dummy(1)), containsInAnyOrder("a", dummy(1)));
    }

    @Test
    public void testUnmodifiableSetOf_RemovesDuplicates() {
        assertThat(unmodifiableSetOf("a", "b", "b", "z"), containsInAnyOrder("a", "b", "z"));
    }

    @Test
    public void testUnmodifiableSetOf_RemovesDuplicates_ArbitraryType() {
        assertThat(unmodifiableSetOf(dummy(1), dummy(2), dummy(2), dummy(10)),
                containsInAnyOrder(dummy(1), dummy(2), dummy(10)));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableSetOf_CreatesStructurallyUnmodifiableSet_CannotRemoveElement() {
        Set<String> set = unmodifiableSetOf("a", "b", "c");

        set.remove("b");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableSetOf_CreatesStructurallyUnmodifiableSet_CannotAddElement() {
        Set<String> set = unmodifiableSetOf("a", "b", "c");

        set.add("d");
    }

    @Test
    public void testAlwaysEmptySet() {
        assertThat(alwaysEmptySet(), is(empty()));
    }

    @Test
    public void testAlwaysEmptySet_ArbitraryType() {
        Set<DummyValue> alwaysEmptySet = alwaysEmptySet();
        assertThat(alwaysEmptySet, is(empty()));
    }

    @Characterization
    @Test(expected = UnsupportedOperationException.class)
    public void testAlwaysEmptySet_CreatesStructurallyUnmodifiableSet_CannotAddElement() {
        Set<String> set = alwaysEmptySet();

        set.add("a");
    }

}
