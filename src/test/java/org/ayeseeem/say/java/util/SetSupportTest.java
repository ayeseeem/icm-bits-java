package org.ayeseeem.say.java.util;

import static org.ayeseeem.say.java.util.DummyValue.dummy;
import static org.ayeseeem.say.java.util.SetSupport.modifiableSetOf;
import static org.ayeseeem.say.java.util.SetSupport.setOf;
import static org.ayeseeem.say.java.util.SetSupport.unmodifiableSetOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

import java.util.Set;

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

    @Test
    public void testSetOf_CreatesStructurallyModifiableSet() {
        Set<String> set = setOf("a", "b", "c");
        set.remove("b");
        assertThat(set, containsInAnyOrder("a", "c"));

        set.add("d");
        set.add("e");
        assertThat(set, containsInAnyOrder("a", "c", "d", "e"));
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
    public void testUnmodifiableSetOf_CreatesStructurallyUnmodifiableList_CannotRemoveElement() {
        Set<String> set = unmodifiableSetOf("a", "b", "c");

        set.remove("b");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableSetOf_CreatesStructurallyUnmodifiableList_CannotAddElement() {
        Set<String> set = unmodifiableSetOf("a", "b", "c");

        set.add("d");
    }

}
