package org.ayeseeem.say;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

public class ContainedItemTest {

    @Test
    public void testIsIn_Collection() {
        Collection<String> collection = Arrays.asList("aaa", "bbb", "ccc");

        assertThat(ContainedItem.the("aaa").isIn(collection), is(true));
        assertThat(ContainedItem.the("bbb").isIn(collection), is(true));
        assertThat(ContainedItem.the("ccc").isIn(collection), is(true));

        assertThat(ContainedItem.the("zzz").isIn(collection), is(false));
    }

    @Test
    public void testIsIn_Array() {
        String[] array = new String[] { "aaa", "bbb", "ccc" };

        assertThat(ContainedItem.the("aaa").isIn(array), is(true));
        assertThat(ContainedItem.the("bbb").isIn(array), is(true));
        assertThat(ContainedItem.the("ccc").isIn(array), is(true));

        assertThat(ContainedItem.the("zzz").isIn(array), is(false));
    }

    @Test
    public void testIsIn_Array_PrimitiveTypesMustBoxed() {
        Integer[] array = new Integer[] { 111, 222, 333 };

        assertThat(ContainedItem.the(111).isIn(array), is(true));
        assertThat(ContainedItem.the(222).isIn(array), is(true));
        assertThat(ContainedItem.the(333).isIn(array), is(true));

        assertThat(ContainedItem.the(888).isIn(array), is(false));
    }

    @Test
    public void testIsOneOf() {
        assertThat(ContainedItem.the("aaa").isOneOf("aaa", "bbb", "ccc"), is(true));
        assertThat(ContainedItem.the("bbb").isOneOf("aaa", "bbb", "ccc"), is(true));
        assertThat(ContainedItem.the("ccc").isOneOf("aaa", "bbb", "ccc"), is(true));

        assertThat(ContainedItem.the("zzz").isOneOf("aaa", "bbb", "ccc"), is(false));
    }

    @Test
    public void testIsOneOf_HandlesPrimitives() {
        assertThat(ContainedItem.the(111).isOneOf(111, 222, 333), is(true));
        assertThat(ContainedItem.the(222).isOneOf(111, 222, 333), is(true));
        assertThat(ContainedItem.the(333).isOneOf(111, 222, 333), is(true));

        assertThat(ContainedItem.the(888).isOneOf(111, 222, 333), is(false));
    }

    @Test
    public void testContainedItem_WrapsTheItem() {
        Collection<String> collection = Arrays.asList("aaa", "bbb", "ccc");

        String item = "aaa";
        assertThat(ContainedItem.the(item).isIn(collection), is(true));

        item = "zzz";
        assertThat(ContainedItem.the(item).isIn(collection), is(false));
    }

}
