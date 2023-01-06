package org.ayeseeem.test.hamcrest;

import static org.ayeseeem.test.hamcrest.Diagnosably.diagnosably;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import org.ayeseeem.test.Characterization;
import org.junit.Test;

public class DiagnosablyTest {

    @Test
    public void testMismatch_InMilliseconds() {
        assertThat(localCalendar("2020-01-01T00:00:00.111"),
                is(diagnosably(localCalendar("2020-01-01T00:00:00.111"))));

        Error er = assertThrows(AssertionError.class, () -> {
            assertThat(localCalendar("2020-01-01T00:00:00.111"),
                    is(diagnosably(localCalendar("2020-01-01T00:00:00.222"))));
        });
        assertThat(er.getMessage(), containsString("Expected: is a Calendar of <2020-01-01T00:00:00.222Z>"));
        assertThat(er.getMessage(), containsString("but: was a Calendar of <2020-01-01T00:00:00.111Z>"));
    }

    @Characterization
    @Test
    public void testMismatch_ViaConstructor() {
        assertThat(localCalendar("2020-01-01T00:00:00.111"),
                is(new Diagnosably(localCalendar("2020-01-01T00:00:00.111"))));

        Error er = assertThrows(AssertionError.class, () -> {
            assertThat(localCalendar("2020-01-01T00:00:00.111"),
                    is(new Diagnosably(localCalendar("2020-01-01T00:00:00.222"))));
        });
        assertThat(er.getMessage(), containsString("Expected: is a Calendar of <2020-01-01T00:00:00.222Z>"));
        assertThat(er.getMessage(), containsString("but: was a Calendar of <2020-01-01T00:00:00.111Z>"));
    }

    @Characterization
    @Test
    public void testMismatch_MillisecondsNotShownInErrorMessage_WhenZero() {
        assertThat(localCalendar("2020-01-01T00:00:00.000"),
                is(diagnosably(localCalendar("2020-01-01T00:00:00.000"))));

        Error er = assertThrows(AssertionError.class, () -> {
            assertThat(localCalendar("2020-01-01T00:00:00.123"),
                    is(diagnosably(localCalendar("2020-01-01T00:00:00.000"))));
        });
        assertThat(er.getMessage(), containsString("Expected: is a Calendar of <2020-01-01T00:00:00Z>"));
        assertThat(er.getMessage(), containsString("but: was a Calendar of <2020-01-01T00:00:00.123Z>"));
    }

    @Characterization
    @Test
    public void exampleHamcrestBehaviour_WhenTheExpected_IsNull() {
        Error er = assertThrows(AssertionError.class, () -> {
            assertThat("the string", is((String) null));
        });
        assertThat(er.getMessage(), containsString("Expected: is null"));
        assertThat(er.getMessage(), containsString("but: was \"the string\""));
    }

    @Characterization
    @Test
    public void exampleHamcrestBehaviour_WhenTheExpected_IsNullValue() {
        Error er = assertThrows(AssertionError.class, () -> {
            assertThat("the string", is(nullValue()));
        });
        assertThat(er.getMessage(), containsString("Expected: is null"));
        assertThat(er.getMessage(), containsString("but: was \"the string\""));
    }

    @Test
    public void testWhenTheExpected_IsNull() {
        Error er = assertThrows(AssertionError.class, () -> {
            assertThat(localCalendar("2020-01-01T00:00:00.123"), is(diagnosably(null)));
        });
        assertThat(er.getMessage(), containsString("Expected: is null"));
        assertThat(er.getMessage(), containsString("but: was a Calendar of <2020-01-01T00:00:00.123Z>"));
    }

    @Characterization
    @Test
    public void exampleHamcrestBehaviour_WhenTheActual_IsNull() {
        Error er = assertThrows(AssertionError.class, () -> {
            assertThat(null, is("the string"));
        });
        assertThat(er.getMessage(), containsString("Expected: is \"the string\""));
        assertThat(er.getMessage(), containsString("but: was null"));
    }

    @Test
    public void testWhenTheActual_IsNull() {
        Error er = assertThrows(AssertionError.class, () -> {
            assertThat(null, is(diagnosably(localCalendar("2020-01-01T00:00:00.001"))));
        });
        assertThat(er.getMessage(), containsString("Expected: is a Calendar of <2020-01-01T00:00:00.001Z>"));
        assertThat(er.getMessage(), containsString("but: was null"));
    }

    @Characterization
    @Test
    public void exampleHamcrestBehaviour_WhenBothNull_Passes() {
        assertThat((String) null, is(nullValue()));
        assertThat((String) null, is((String) null));
    }

    @Characterization
    @Test
    public void testWhenBothNull_BehavesDIfferentlyToPlainIs_Fails_ButMessageIsParadoxical() {
        assertThat((Calendar) null, is(nullValue()));
        assertThat((Calendar) null, is((Calendar) null));

        // TODO: ICM 2023-01-03: There's a fix: check for both null in matcher
        Error er = assertThrows(AssertionError.class, () -> {
            assertThat((Calendar) null, is(diagnosably((Calendar) null)));
        });
        assertThat(er.getMessage(), containsString("Expected: is null"));
        assertThat(er.getMessage(), containsString("but: was null"));
    }

    private static Calendar localCalendar(String isoLocalDateTime) {
        LocalDateTime local = LocalDateTime.parse(isoLocalDateTime);
        ZonedDateTime zoned = local.atZone(ZoneId.systemDefault());

        return localCalendar(zoned.toInstant());
    }

    private static Calendar localCalendar(Instant instant) {
        return localCalendar(Date.from(instant));
    }

    private static Calendar localCalendar(Date date) {
        Calendar calendar = localCalendarNow();
        calendar.setTime(date);
        return calendar;
    }

    private static Calendar localCalendarNow() {
        return Calendar.getInstance();
    }

}
