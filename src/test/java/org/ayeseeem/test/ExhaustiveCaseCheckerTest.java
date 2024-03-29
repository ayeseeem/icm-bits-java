package org.ayeseeem.test;

import static org.ayeseeem.say.java.util.SetSupport.setOf;
import static org.ayeseeem.test.ExhaustiveCaseCheckerTest.Cases.ONE;
import static org.ayeseeem.test.ExhaustiveCaseCheckerTest.Cases.THREE;
import static org.ayeseeem.test.ExhaustiveCaseCheckerTest.Cases.TWO;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.fail;

import org.ayeseeem.test.ExhaustiveCaseChecker.ExhaustiveCaseCheck;
import org.junit.Test;

public class ExhaustiveCaseCheckerTest {

    enum Cases {
        ONE, TWO, THREE
    }

    @Test
    public void testCheckAll_SimpleExample_Failing() {
        ExhaustiveCaseChecker<Cases> subject = new ExhaustiveCaseChecker<>(
                setOf(Cases.values()));

        try {
            subject.checkAll(
                    () -> assertThat(ONE.name(), is("ONE")),
                    () -> assertThat(TWO.name(), is("TWO")),
                    () -> assertThat(THREE.name() + "oops!", is("THREE")));
        } catch (AssertionError e) {
            assertThat(e.getMessage(), containsString("Expected: is \"THREE\""));
            assertThat(e.getMessage(), containsString("but: was \"THREEoops!\""));
            return;

        }

        fail(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void testCheckAll_CapturesTheCases() {
        ExhaustiveCaseChecker<Cases> subject = new ExhaustiveCaseChecker<>(setOf(Cases.values()));

        assertThat(subject.getCases(), containsInAnyOrder(ONE, TWO, THREE));
    }

    @Test
    public void testCheckAll_UsingAlternativeEnumConstructor_CapturesTheCases() {
        ExhaustiveCaseChecker<Cases> subject = new ExhaustiveCaseChecker<>(Cases.class);

        assertThat(subject.getCases(), containsInAnyOrder(ONE, TWO, THREE));
    }

    @Test
    public void testCheckAll_UsingStaticFactoryMethodForEnum_CapturesTheCases() {
        ExhaustiveCaseChecker<Cases> subject = ExhaustiveCaseChecker.forEnum(Cases.class);

        assertThat(subject.getCases(), containsInAnyOrder(ONE, TWO, THREE));
    }

    @Test
    public void testCheckAll_FailsIfTooFewCases() {
        ExhaustiveCaseChecker<Cases> subject = new ExhaustiveCaseChecker<>(
                setOf(Cases.values()));

        try {
            subject.checkAll(NO_OP, NO_OP);
        } catch (AssertionError e) {
            assertThat(e.getMessage(), containsString("Unexpected number of checks"));
            return;
        }

        fail(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void testCheckAll_FailsIfTooManyCases() {
        ExhaustiveCaseChecker<Cases> subject = new ExhaustiveCaseChecker<>(
                setOf(Cases.values()));

        try {
            subject.checkAll(NO_OP, NO_OP, NO_OP, NO_OP);
        } catch (AssertionError e) {
            assertThat(e.getMessage(), containsString("Unexpected number of checks"));
            return;
        }

        fail(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void testCheckAll_FailsIfTooFewCases_MessageDetails() {
        ExhaustiveCaseChecker<Cases> subject = new ExhaustiveCaseChecker<>(
                setOf(Cases.values()));

        try {
            subject.checkAll(NO_OP);
        } catch (AssertionError e) {
            assertThat(e.getMessage(), containsString("Expected: is <3>"));
            assertThat(e.getMessage(), containsString("but: was <1>"));
            assertThat(e.getMessage(), containsString("Unexpected number of checks"));
            return;
        }

        fail(ASSERTION_ERROR_EXPECTED);
    }

    @Test
    public void testCheckAll_FailsIfTooManyCases_MessageDetails() {
        ExhaustiveCaseChecker<Cases> subject = new ExhaustiveCaseChecker<>(
                setOf(Cases.values()));

        try {
            subject.checkAll(NO_OP, NO_OP, NO_OP, NO_OP);
        } catch (AssertionError e) {
            assertThat(e.getMessage(), containsString("Expected: is <3>"));
            assertThat(e.getMessage(), containsString("but: was <4>"));
            assertThat(e.getMessage(), containsString("Unexpected number of checks"));
            return;
        }

        fail(ASSERTION_ERROR_EXPECTED);
    }

    @Characterization
    @Test
    public void testCheckAll_DoesNotDetectIfNotAllCasesAreCovered() {
        ExhaustiveCaseChecker<Cases> subject = new ExhaustiveCaseChecker<>(
                setOf(Cases.values()));

        subject.checkAll(
                () -> assertThat(ONE.name(), is("ONE")),
                () -> assertThat(ONE.name(), is("ONE")),
                () -> assertThat(THREE.name(), is("THREE")));
    }

    @Characterization
    @Test
    public void testCheckAll_DoesNotDetectTestCasesAreInvokedAtAll() {
        ExhaustiveCaseChecker<Cases> subject = new ExhaustiveCaseChecker<>(
                setOf(Cases.values()));

        subject.checkAll(
                () -> assertThat(ONE.name(), is("ONE")),
                () -> assertThat(TWO.name(), is("TWO")),
                () -> assertThat("arbitrary unrelated passing case", is("arbitrary unrelated passing case")));
    }

    private static final String ASSERTION_ERROR_EXPECTED = "AssertionError expected";

    private static final ExhaustiveCaseCheck NO_OP = () -> {
        // "No operation" - does nothing
    };

}
