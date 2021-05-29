package org.ayeseeem.test.example;

import static org.ayeseeem.say.java.util.SetSupport.setOf;
import static org.ayeseeem.test.example.ExhaustiveCaseCheckerExampleTest.Cases.ONE;
import static org.ayeseeem.test.example.ExhaustiveCaseCheckerExampleTest.Cases.THREE;
import static org.ayeseeem.test.example.ExhaustiveCaseCheckerExampleTest.Cases.TWO;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.EnumSet;

import org.ayeseeem.test.ExhaustiveCaseChecker;
import org.junit.Test;

public class ExhaustiveCaseCheckerExampleTest {

    enum Cases {
        ONE, TWO, THREE
    }

    @Test
    public void testCheckAll_SimpleExample() {
        ExhaustiveCaseChecker<Cases> subject = new ExhaustiveCaseChecker<>(
                setOf(Cases.values()));

        subject.checkAll(
                () -> assertThat(ONE.name(), is("ONE")),
                () -> assertThat(TWO.name(), is("TWO")),
                () -> assertThat(THREE.name(), is("THREE")));
    }

    @Test
    public void testCheckAll_SimpleExample_UsingAlternativeEnumSyntax() {
        ExhaustiveCaseChecker<Cases> subject = new ExhaustiveCaseChecker<>(
                EnumSet.allOf(Cases.class));

        subject.checkAll(
                () -> assertThat(ONE.name(), is("ONE")),
                () -> assertThat(TWO.name(), is("TWO")),
                () -> assertThat(THREE.name(), is("THREE")));
    }

    @Test
    public void testCheckAll_SimpleExample_UsingAlternativeEnumConstructor() {
        ExhaustiveCaseChecker<Cases> subject = new ExhaustiveCaseChecker<>(Cases.class);

        subject.checkAll(
                () -> assertThat(ONE.name(), is("ONE")),
                () -> assertThat(TWO.name(), is("TWO")),
                () -> assertThat(THREE.name(), is("THREE")));
    }

    @Test
    public void testCheckAll_SimpleExample_UsingStaticFactoryMethodForEnum() {
        ExhaustiveCaseChecker<Cases> subject = ExhaustiveCaseChecker.forEnum(Cases.class);

        subject.checkAll(
                () -> assertThat(ONE.name(), is("ONE")),
                () -> assertThat(TWO.name(), is("TWO")),
                () -> assertThat(THREE.name(), is("THREE")));
    }

}
