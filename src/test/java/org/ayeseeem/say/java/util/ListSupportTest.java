package org.ayeseeem.say.java.util;

import static org.ayeseeem.say.java.util.DummyValue.dummy;
import static org.ayeseeem.say.java.util.ListSupport.alwaysEmptyList;
import static org.ayeseeem.say.java.util.ListSupport.emptyList;
import static org.ayeseeem.say.java.util.ListSupport.initiallyEmptyList;
import static org.ayeseeem.say.java.util.ListSupport.listOf;
import static org.ayeseeem.say.java.util.ListSupport.modifiableListOf;
import static org.ayeseeem.say.java.util.ListSupport.unmodifiableListOf;
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

    @Characterization
    @Test
    public void testListOf_TakesAnArrayAsAnArgument() {
        String[] anArray = { "a", "b", "c" };

        assertThat(listOf(anArray), contains("a", "b", "c"));
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

    @Test(expected = UnsupportedOperationException.class)
    public void testListOf_CreatesStructurallyUnmodifiableList_CannotRemoveElement() {
        List<String> list = listOf("a", "b", "c");

        list.remove("b");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testListOf_CreatesStructurallyUnmodifiableList_CannotAddElement() {
        List<String> list = listOf("a", "b", "c");

        list.add("d");
    }

    @Test
    public void testListOf_CreatesListWhereElementsCanBeSubstituted() {
        List<String> list = listOf("a", "b");
        list.set(0, "new entry");
        assertThat(list, contains("new entry", "b"));
    }

    @Test
    public void testEmptyList() {
        assertThat(emptyList(), is(empty()));
    }

    @Test
    public void testEmptyList_Modifiability_Matches_Simple_ListOf() {
        List<String> defaultList = listOf();
        try {
            defaultList.add("a");
        } catch (UnsupportedOperationException possible) {
            // might be unmodifiable
        }

        List<String> emptyList = emptyList();
        try {
            emptyList.add("a");
        } catch (UnsupportedOperationException possible) {
            // might be unmodifiable
        }

        assertThat(defaultList, is(emptyList));
    }

    @Test
    public void testModifiableListOf() {
        assertThat(modifiableListOf("a", "b", "c"), contains("a", "b", "c"));
    }

    @Test
    public void testModifiableListOf_CanBeEmpty() {
        assertThat(modifiableListOf(), is(empty()));
    }

    @Test
    public void testModifiableListOf_RetainsOrder() {
        assertThat(modifiableListOf("b", "z", "a", "y"), containsInAnyOrder("b", "z", "a", "y"));
        assertThat(modifiableListOf("b", "z", "a", "y"), contains("b", "z", "a", "y"));
    }

    @Characterization
    @Test
    public void testModifiableListOf_TakesAnArrayAsAnArgument() {
        String[] anArray = { "a", "b", "c" };

        assertThat(modifiableListOf(anArray), contains("a", "b", "c"));
    }

    @Test
    public void testModifiableListOf_ArbitraryType() {
        assertThat(modifiableListOf(dummy(1), dummy(2), dummy(3)),
                contains(dummy(1), dummy(2), dummy(3)));
    }

    @Test
    public void testModifiableListOf_AllowsMixedTypes() {
        assertThat(modifiableListOf("a", dummy(1)), contains("a", dummy(1)));
    }

    @Test
    public void testModifiableListOf_CreatesStructurallyModifiableList() {
        List<String> list = modifiableListOf("a", "b", "c");
        list.remove("b");
        assertThat(list, contains("a", "c"));

        list.add("d");
        list.add("e");
        assertThat(list, contains("a", "c", "d", "e"));
    }

    @Test
    public void testModifiableListOf_CreatesListWhereElementsCanBeSubstituted() {
        List<String> list = modifiableListOf("a", "b");
        list.set(0, "new entry");
        assertThat(list, contains("new entry", "b"));
    }

    @Test
    public void testInitiallyEmptyList() {
        assertThat(initiallyEmptyList(), is(empty()));
    }

    @Test
    public void testInitiallyEmptyList_CreatesStructurallyModifiableList() {
        List<String> list = initiallyEmptyList();

        list.add("a");
        list.add("b");
        assertThat(list, contains("a", "b"));
    }

    @Test
    public void testInitiallyEmptyList_CreatesListWhereElementsCanBeSubstituted() {
        List<String> list = initiallyEmptyList();

        list.add("a");
        list.add("b");
        assertThat(list, contains("a", "b"));

        list.set(0, "new entry");
        assertThat(list, contains("new entry", "b"));
    }

    @Test
    public void testUnmodifiableListOf() {
        assertThat(unmodifiableListOf("a", "b", "c"), contains("a", "b", "c"));
    }

    @Test
    public void testUnmodifiableListOf_CanBeEmpty() {
        assertThat(unmodifiableListOf(), is(empty()));
    }

    @Test
    public void testUnmodifiableListOf_RetainsOrder() {
        assertThat(unmodifiableListOf("b", "z", "a", "y"), containsInAnyOrder("b", "z", "a", "y"));
        assertThat(unmodifiableListOf("b", "z", "a", "y"), contains("b", "z", "a", "y"));
    }

    @Characterization
    @Test
    public void testUnmodifiableListOf_TakesAnArrayAsAnArgument() {
        String[] anArray = { "a", "b", "c" };

        assertThat(unmodifiableListOf(anArray), contains("a", "b", "c"));
    }

    @Test
    public void testUnmodifiableListOf_ArbitraryType() {
        assertThat(unmodifiableListOf(dummy(1), dummy(2), dummy(3)),
                contains(dummy(1), dummy(2), dummy(3)));
    }

    @Test
    public void testUnmodifiableListOf_AllowsMixedTypes() {
        assertThat(unmodifiableListOf("a", dummy(1)), contains("a", dummy(1)));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableListOf_CreatesStructurallyUnmodifiableList_CannotRemoveElement() {
        List<String> list = unmodifiableListOf("a", "b", "c");

        list.remove("b");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableListOf_CreatesStructurallyUnmodifiableList_CannotAddElement() {
        List<String> list = unmodifiableListOf("a", "b", "c");

        list.add("d");
    }

    @Characterization
    @Test
    public void testUnmodifiableListOf_CreatesListWhereElementsCanBeSubstituted() {
        List<String> list = unmodifiableListOf("a", "b");
        list.set(0, "new entry");
        assertThat(list, contains("new entry", "b"));
    }

    @Test
    public void testAlwaysEmptyList() {
        assertThat(alwaysEmptyList(), is(empty()));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAlwaysEmptyList_CreatesStructurallyUnmodifiableList_CannotAddElement() {
        List<String> list = alwaysEmptyList();

        list.add("a");
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
