package org.ayeseeem.say.example;

import static java.time.Month.DECEMBER;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static org.ayeseeem.say.java.util.ListSupport.listOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import java.time.Month;
import java.util.List;

import org.junit.Test;

public class ListSupportExamplesTest {

    @Test
    public void testListOfExample() {
        List<Month> latinNumberedPeriod = listOf(SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER);

        assertThat(latinNumberedPeriod, contains(SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER));
    }

}
