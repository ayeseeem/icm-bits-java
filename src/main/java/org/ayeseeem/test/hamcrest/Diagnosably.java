package org.ayeseeem.test.hamcrest;

import static org.hamcrest.core.IsEqual.equalTo;

import java.time.Instant;
import java.util.Calendar;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

//TODO: ICM 2023-01-03: What if time zones are different - how do we show that, if the Instants match?
//TODO: ICM 2023-01-03: What if Calendars differ in some detail somehow, but the Instants match?

/**
 * Matcher for comparing {@code Calendar}s but giving an {@link Instant}
 * representation <em>in the diagnostic</em> as it's (usually) much easier to
 * compare the times of.
 */
public class Diagnosably extends BaseMatcher<Calendar> {

    @Factory
    public static Matcher<Calendar> diagnosably(Calendar expected) {
        return new Diagnosably(expected);
    }

    private final Calendar expectedCalendar;

    public Diagnosably(Calendar calendar) {
        this.expectedCalendar = calendar;
    }

    @Override
    public void describeTo(Description description) {
        describeCalendar(expectedCalendar, description);
    }

    @Override
    public void describeMismatch(Object item, Description description) {
        description.appendText("was ");
        describeCalendar((Calendar) item, description);
    }

    private static void describeCalendar(Calendar calendar, Description description) {
        if (calendar == null) {
            description.appendValue(null);
        } else {
            description.appendText("a Calendar of ");
            description.appendValue(calendar.toInstant());
        }
    }

    @Override
    public boolean matches(Object item) {
        if (item == null && expectedCalendar == null) {
            return true;
        }
        if (item == null) {
            return false;
        }
        if (!(item instanceof Calendar)) {
            return false;
        }

        Instant instant = ((Calendar) item).toInstant();
        if (expectedCalendar != null) {
            Instant expectedInstant = expectedCalendar.toInstant();
            Matcher<? super Instant> instantMatcher = equalTo(expectedInstant);
            if (instantMatcher.matches(instant)) {
                return true;
            }
        }
        return false;
    }

}
