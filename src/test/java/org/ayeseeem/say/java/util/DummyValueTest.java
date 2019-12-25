package org.ayeseeem.say.java.util;

import static org.ayeseeem.say.java.util.DummyValue.dummy;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.ayeseeem.test.Characterization;
import org.junit.Test;

public class DummyValueTest {

    @Test
    public void testEquals() {
        assertThat(dummy(111).equals(dummy(111)), is(true));
        assertThat(dummy(111).equals(dummy(222)), is(false));
    }

    @Test
    public void testEquals_Self() {
        DummyValue self = dummy(123);

        assertThat(self.equals(self), is(true));
    }

    @Test
    public void testEquals_WorksWithHamcrest() {
        assertThat(dummy(111), is(dummy(111)));
        assertThat(dummy(111), is(not(dummy(222))));

        DummyValue self = dummy(111);
        assertThat(self, is(self));
    }

    @Test
    public void testStaticFactory() {
        assertThat(dummy(111), is(dummy(111)));
    }

    @Characterization
    @Test
    public void testStaticFactory_CreatesNewInstances() {
        DummyValue d1 = dummy(111);
        DummyValue d2 = dummy(111);
        assertThat(d1 != d2, is(true));
    }

}
