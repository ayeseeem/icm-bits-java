package org.ayeseeem.say.java.util;

import static org.ayeseeem.say.java.util.DummyValue.dummy;
import static org.ayeseeem.say.java.util.ListSupport.listOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;

import java.util.Arrays;
import java.util.List;

import org.ayeseeem.test.Characterization;
import org.junit.Test;

public class ListSupportTest {

    @Test
    public void testListOf() {
        assertThat(listOf("a", "b", "c"), contains("a", "b", "c"));
    }

    @Test
    public void testListOf_CanBeEmpty() {
        assertThat(listOf(), is(empty()));
    }

    @Test
    public void testListOf_RetainsOrder() {
        assertThat(listOf("b", "z", "a", "y"), containsInAnyOrder("b", "z", "a", "y"));
        assertThat(listOf("b", "z", "a", "y"), contains("b", "z", "a", "y"));
    }

    @Test
    public void testListOf_ArbitraryType() {
        assertThat(listOf(dummy(1), dummy(2), dummy(3)),
                contains(dummy(1), dummy(2), dummy(3)));
    }

    @Test
    public void testListOf_AllowsMixedTypes() {
        assertThat(listOf("a", dummy(1)), contains("a", dummy(1)));
    }

    @Test
    public void testListOf_CreatesStructurallyModifiableList() {
        List<String> list = listOf("a", "b", "c");
        list.remove("b");
        assertThat(list, contains("a", "c"));

        list.add("d");
        list.add("e");
        assertThat(list, contains("a", "c", "d", "e"));
    }

    @Test
    public void testListOf_CreatesListWhereElementsCanBeSubstituted() {
        List<String> list = listOf("a", "b");
        list.set(0, "new entry");
        assertThat(list, contains("new entry", "b"));
    }

    @Characterization
    @Test
    public void investigateArraysAsList_AllowsMixedTypes() {
        assertThat(Arrays.asList("a", dummy(1)), contains("a", dummy(1)));
    }

    @Characterization
    @Test(expected = UnsupportedOperationException.class)
    public void investigateArraysAsList_CreatesUnmodifiableList() {
        List<String> list = Arrays.asList("a", "b");
        list.remove(0);
    }

    @Characterization
    @Test
    public void investigateArraysAsList_CreatesListWhereElementsCanBeSubstituted() {
        List<String> list = Arrays.asList("a", "b");
        list.set(0, "new entry");
        assertThat(list, contains("new entry", "b"));
    }

}
