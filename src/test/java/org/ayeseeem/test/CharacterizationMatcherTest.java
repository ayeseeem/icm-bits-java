package org.ayeseeem.test;

import static org.ayeseeem.test.CharacterizationMatcher.currently;
import static org.ayeseeem.test.CharacterizationMatcher.ideally;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class CharacterizationMatcherTest {

    @Test
    public void testIdeally() {
        // Example pass
        assertThat("The odd result",
                ideally(is("The conventional result"))
                .butCurrently(is("The odd result")));
        assertThat("The odd result",
                ideally(containsString("conventional"))
                .butCurrently(containsString("odd")));

        assertThat(41,
                ideally(is(42))
                .butCurrently(is(41)));
        assertThat(42.01,
                ideally(is(42.0))
                .butCurrently(is(42.01)));

        // Behaviour for fail case
        AssertionError expected = assertThrows(AssertionError.class, () -> {
            assertThat("The conventional result",
                    ideally(is("The conventional result"))
                    .butCurrently(is("The odd result")));
        });
        assertThat(expected.getMessage(),
                containsString("Expected: (is \"The odd result\" and not is \"The conventional result\")"));
        assertThat(expected.getMessage(),
                containsString("but: is \"The odd result\" was \"The conventional result\""));
    }

    @Test
    public void testCurrently() {
        // Example pass
        assertThat("The odd result",
                currently(is("The odd result"))
                .butIdeally(is("The conventional result")));
        assertThat("The odd result",
                currently(containsString("odd"))
                .butIdeally(containsString("conventional")));

        assertThat(41,
                currently(is(41))
                .butIdeally(is(42)));
        assertThat(42.01,
                currently(is(42.01))
                .butIdeally(is(42.0)));

        // Behaviour for fail case
        AssertionError expected = assertThrows(AssertionError.class, () -> {
            assertThat("The conventional result",
                    currently(is("The odd result"))
                    .butIdeally(is("The conventional result")));
        });

        assertThat(expected.getMessage(),
                containsString("Expected: (is \"The odd result\" and not is \"The conventional result\")"));
        assertThat(expected.getMessage(),
                containsString("but: is \"The odd result\" was \"The conventional result\""));
    }

}
