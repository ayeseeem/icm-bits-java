package org.ayeseeem.test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;

import org.hamcrest.Matcher;

public class CharacterizationMatcher {

    public static <T> CurrentMatcherBuilder<T> ideally(Matcher<? super T> ideal) {
        return new CurrentMatcherBuilder<>(ideal);
    }

    public static <T> IdealMatcherBuilder<T> currently(Matcher<? super T> current) {
        return new IdealMatcherBuilder<>(current);
    }

    public static class CurrentMatcherBuilder<T> {
        private final Matcher<? super T> ideal;

        public CurrentMatcherBuilder(Matcher<? super T> ideal) {
            this.ideal = ideal;
        }

        public Matcher<? super T> butCurrently(Matcher<? super T> current) {
            // TODO: ICM 2022-01-29: Return a special CharacterizationMatcher, that encapsulates the behaviour and provides a better message
            return allOf(current, not(ideal));
        }
    }

    public static class IdealMatcherBuilder<T> {
        private final Matcher<? super T> current;

        public IdealMatcherBuilder(Matcher<? super T> current) {
            this.current = current;
        }

        public Matcher<? super T> butIdeally(Matcher<? super T> ideal) {
            return allOf(current, not(ideal));
        }
    }

}
