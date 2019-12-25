package org.ayeseeem.say.example;

import static java.time.Month.DECEMBER;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static org.ayeseeem.say.java.util.SetSupport.setOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

import java.time.Month;
import java.util.Set;

import org.junit.Test;

public class SetSupportExamplesTest {

    @Test
    public void testListOfExample() {
        Set<Month> latinNumberedMonths = setOf(SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER);

        assertThat(latinNumberedMonths, containsInAnyOrder(SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER));
    }

}
