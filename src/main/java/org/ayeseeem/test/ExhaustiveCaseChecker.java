package org.ayeseeem.test;

import static org.ayeseeem.say.java.util.ListSupport.listOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.EnumSet;
import java.util.Set;

import org.hamcrest.Matcher;

/**
 * Executes a set of tests to exhaustively test a set of items.
 * <p>
 * The main purpose of this is to detect when you expand a set of items but
 * forget to add another test case. But it will also detect if you add too many.
 * <p>
 * The use case that inspired this was when testing the names and values of some
 * enums that were being persisted to a database. Each name or value was tested,
 * to verify that it hadn't changed. But if another enum value was added,
 * there was nothing to ensure that an extra test case would be added.
 *
 * @param <CaseT> the type of item to be tested
 */
public class ExhaustiveCaseChecker<CaseT> {

    @FunctionalInterface
    public interface ExhaustiveCaseCheck {
        void test();
    }

    @FunctionalInterface
    public interface ExhaustiveCaseAssertion<CaseT> {
        void test(CaseT actual, Matcher<? super CaseT> matcher);
    }

    private final Set<CaseT> cases;

    /**
     * Gets the cases, for checking in tests.
     * @return the cases
     */
    Set<CaseT> getCases() {
        return cases;
    }

    public ExhaustiveCaseChecker(Set<CaseT> cases) {
        this.cases = cases;
    }

    public static <E extends Enum<E>> ExhaustiveCaseChecker<E> forEnum(Class<E> enumType) {
        EnumSet<E> s = EnumSet.allOf(enumType);
        return new ExhaustiveCaseChecker<>(s);
    }

    @SuppressWarnings("unchecked")
    public <E extends Enum<E>> ExhaustiveCaseChecker(Class<E> enumType) {
        EnumSet<E> s = EnumSet.allOf(enumType);
        this.cases = (Set<CaseT>) s;
    }

    public void checkAll(ExhaustiveCaseCheck... checks) {
        assertThat("Unexpected number of checks", checks.length, is(cases.size()));

        listOf(checks).forEach(ExhaustiveCaseCheck::test);
    }

}
