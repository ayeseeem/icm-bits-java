package org.ayeseeem.test.example;

import static org.ayeseeem.test.CharacterizationMatcher.ideally;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CharacterizationMatcherExampleTest {

    @Test
    public void testIdeally_ExampleUsage() {

        BookStore store = new BookStore();

        // Instead of this:
        assertThat(store.findBooks("unknown author"),
                is(nullValue())); // should be empty

        // You can write this:
        assertThat(store.findBooks("unknown author"),
                ideally(is(empty()))
                .butCurrently(is(nullValue())));

        // Then if the behaviour is ever fixed...
        store.fix();

        // The fix is detected and flagged...
        AssertionError expected = assertThrows(AssertionError.class, () -> {
            assertThat(store.findBooks("unknown author"),
                    ideally(is(empty()))
                    .butCurrently(is(nullValue())));
        });
        assertThat(expected.getMessage(),
                containsString("Expected: (is null and not is an empty collection)"));
        assertThat(expected.getMessage(),
                containsString("but: is null was <[]>"));
    }

    private class BookStore {
        private boolean isFixed = false;

        public List<String> findBooks(String author) {
            if (author == "me") {
                return Arrays.asList("Another book about Java");
            }

            if (isFixed) {
                return Arrays.asList();
            }
            return null;
        }

        public void fix() {
            isFixed = true;
        }

    }

}
