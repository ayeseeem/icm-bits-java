package org.ayeseeem.say.java.util;

import static org.ayeseeem.say.java.util.MapSupport.insertionOrderedMap;
import static org.ayeseeem.say.java.util.MapSupport.keySortedMap;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.instanceOf;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

public class MapSupportTest {

    @Test
    public void testKeySortedMap_KeysAreSorted() {
        Map<Integer, String> result = keySortedMap();
        result.put(9, "some string");
        result.put(1, "some string");
        result.put(5, "some string");

        assertThat(result.keySet(), contains(1, 5, 9));
    }

    @Test
    public void testKeySortedMap_KeysAreSorted_NotCoincidentallySortedByValue() {
        Map<Integer, String> result = keySortedMap();
        result.put(9, "some differently unsorted string aaa");
        result.put(3, "some differently unsorted string bbb");
        result.put(1, "some differently unsorted string zzz");
        result.put(7, "some differently unsorted string yyy");

        assertThat(result.keySet(), contains(1, 3, 7, 9));
    }

    @Test
    public void testKeySortedMap_KeysIterateInOrder() {
        Map<Integer, String> result = keySortedMap();
        result.put(9, "some string");
        result.put(1, "some string");
        result.put(5, "some string");

        Iterator<Integer> keysIterator = result.keySet().iterator();
        assertThat(keysIterator.next(), is(1));
        assertThat(keysIterator.next(), is(5));
        assertThat(keysIterator.next(), is(9));
    }

    @Test
    public void testKeySortedMap_EntriesAreSortedByKey() {
        Map<Integer, String> result = keySortedMap();
        result.put(9, "some string");
        result.put(1, "some string");
        result.put(5, "some string");

        Iterator<Entry<Integer, String>> entriesIterator = result.entrySet().iterator();
        assertThat(entriesIterator.next(), is(new SimpleEntry<>(1, "some string")));
        assertThat(entriesIterator.next(), is(new SimpleEntry<>(5, "some string")));
        assertThat(entriesIterator.next(), is(new SimpleEntry<>(9, "some string")));
    }

    @Test
    public void testKeySortedMap_EntriesAreSortedByKey_NotCoincidentallySortedByValue() {
        Map<Integer, String> result = keySortedMap();
        result.put(9, "some differently unsorted string aaa");
        result.put(3, "some differently unsorted string bbb");
        result.put(1, "some differently unsorted string zzz");
        result.put(7, "some differently unsorted string yyy");

        Iterator<Entry<Integer, String>> entriesIterator = result.entrySet().iterator();
        assertThat(entriesIterator.next(), is(new SimpleEntry<>(1, "some differently unsorted string zzz")));
        assertThat(entriesIterator.next(), is(new SimpleEntry<>(3, "some differently unsorted string bbb")));
        assertThat(entriesIterator.next(), is(new SimpleEntry<>(7, "some differently unsorted string yyy")));
        assertThat(entriesIterator.next(), is(new SimpleEntry<>(9, "some differently unsorted string aaa")));
    }

    @Test
    public void testKeySortedMap_CanBeEmpty() {
        Map<Integer, String> result = keySortedMap();

        assertThat(result.isEmpty(), is(true));
    }

    @Test
    public void testKeySortedMap_ImplementationDetails() {
        Map<Integer, String> result = keySortedMap();

        assertThat(result, is(instanceOf(SortedMap.class)));
        assertThat(result, is(instanceOf(TreeMap.class)));
    }

    @Test
    public void testInsertionOrderedMap_KeysAreInInsertionOrder() {
        Map<Integer, String> result = insertionOrderedMap();
        result.put(9, "some string");
        result.put(1, "some string");
        result.put(5, "some string");

        assertThat(result.keySet(), contains(9, 1, 5));
    }

    @Test
    public void testInsertionOrderedMap_KeysAreInInsertionOrder_NotCoincidentallySortedByValue() {
        Map<Integer, String> result = insertionOrderedMap();
        result.put(9, "some differently unsorted string aaa");
        result.put(3, "some differently unsorted string bbb");
        result.put(1, "some differently unsorted string zzz");
        result.put(7, "some differently unsorted string yyy");

        assertThat(result.keySet(), contains(9, 3, 1, 7));
    }

    @Test
    public void testInsertionOrderedMap_KeysIterateInInsertionOrder() {
        Map<Integer, String> result = insertionOrderedMap();
        result.put(9, "some string");
        result.put(1, "some string");
        result.put(5, "some string");

        Iterator<Integer> keysIterator = result.keySet().iterator();
        assertThat(keysIterator.next(), is(9));
        assertThat(keysIterator.next(), is(1));
        assertThat(keysIterator.next(), is(5));
    }

    @Test
    public void testInsertionOrderedMap_EntriesAreInInsertionOrder() {
        Map<Integer, String> result = insertionOrderedMap();
        result.put(9, "some string");
        result.put(1, "some string");
        result.put(5, "some string");

        Iterator<Entry<Integer, String>> entriesIterator = result.entrySet().iterator();
        assertThat(entriesIterator.next(), is(new SimpleEntry<>(9, "some string")));
        assertThat(entriesIterator.next(), is(new SimpleEntry<>(1, "some string")));
        assertThat(entriesIterator.next(), is(new SimpleEntry<>(5, "some string")));
    }

    @Test
    public void testInsertionOrderedMap_EntriesAreInInsertionOrder_NotCoincidentallySortedByValue() {
        Map<Integer, String> result = insertionOrderedMap();
        result.put(9, "some differently unsorted string aaa");
        result.put(3, "some differently unsorted string bbb");
        result.put(1, "some differently unsorted string zzz");
        result.put(7, "some differently unsorted string yyy");

        Iterator<Entry<Integer, String>> entriesIterator = result.entrySet().iterator();
        assertThat(entriesIterator.next(), is(new SimpleEntry<>(9, "some differently unsorted string aaa")));
        assertThat(entriesIterator.next(), is(new SimpleEntry<>(3, "some differently unsorted string bbb")));
        assertThat(entriesIterator.next(), is(new SimpleEntry<>(1, "some differently unsorted string zzz")));
        assertThat(entriesIterator.next(), is(new SimpleEntry<>(7, "some differently unsorted string yyy")));
    }

    @Test
    public void testInsertionOrderedMap_CanBeEmpty() {
        Map<Integer, String> result = insertionOrderedMap();

        assertThat(result.isEmpty(), is(true));
    }

    @Test
    public void testInsertionOrderedMap_ImplementationDetails() {
        Map<Integer, String> result = insertionOrderedMap();

        assertThat(result, is(instanceOf(HashMap.class)));
        assertThat(result, is(instanceOf(LinkedHashMap.class)));
    }

}
