package org.ayeseeem.say.example;

import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static org.ayeseeem.say.ContainedItem.the;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.nio.charset.Charset;
import java.time.Month;
import java.util.Collection;

import org.junit.Test;

public class ContainedItemExamplesTest {

    public static class IsInCollectionExample {
        Collection<String> charsetNames = Charset.availableCharsets().keySet();

        boolean isSupported(String charsetName) {
            if (the(charsetName).isIn(charsetNames)) {
                return true;
            }
            return false;
        }

        @Test
        public void testExampleContainedItem_IsIn_Collection() {
            assertThat(isSupported("UTF-8"), is(true)); // UTF-8 is always supported
            assertThat(isSupported("WTF-NoSuchEncoding"), is(false));
        }
    }

    public static class IsInArrayExample {
        Integer[] supportedErrorCodes = new Integer[] { 200, 201, 401, 403, 404 };

        boolean isSupported(int errorCode) {
            if (the(errorCode).isIn(supportedErrorCodes)) {
                return true;
            }
            return false;
        }

        @Test
        public void testExampleContainedItem_IsIn_Array() {
            assertThat(isSupported(200), is(true));
            assertThat(isSupported(500), is(false));
        }
    }

    public static class IsOneOfExample {

        boolean isLatinNumbered(Month month) {
            if (the(month).isOneOf(SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER)) {
                return true;
            }
            return false;
        }

        @Test
        public void testExampleContainedItem_IsOneOf() {
            assertThat(isLatinNumbered(SEPTEMBER), is(true));
            assertThat(isLatinNumbered(JANUARY), is(false));
        }
    }

}
