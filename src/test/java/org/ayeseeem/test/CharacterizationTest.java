package org.ayeseeem.test;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Target;

import org.junit.Test;

public class CharacterizationTest {

    @Test
    public void testCharacterization_IsDocumented() {
        Annotation annotation = Characterization.class.getAnnotation(Documented.class);

        assertThat(annotation, is(not(nullValue())));
        assertThat(annotation, is(instanceOf(Documented.class)));
    }

    @Test
    public void testCharacterization_IsTarget() {
        Annotation annotation = Characterization.class.getAnnotation(Target.class);

        assertThat(annotation, is(not(nullValue())));
        assertThat(annotation, is(instanceOf(Target.class)));
    }

    @Test
    public void testCharacterization_Targets() {
        Target target = Characterization.class.getAnnotation(Target.class);

        assertThat(target.value(), is(arrayContainingInAnyOrder(TYPE, METHOD)));
    }

    @Characterization
    private class AnnotatedClass {
        // does nothing
    }

    @Characterization
    @Test
    public void appliedAnnotationIsNotCurrentlyRetainedAtRuntime() {
        Annotation annotation = AnnotatedClass.class.getAnnotation(Characterization.class);

        assertThat(annotation, is(nullValue()));
    }

    @Characterization("Cannot test value/reason - not currently retained at runtime")
    @Test
    public void exampleCharacterization_Usage() {

        @Characterization
        final class BareAnnnotation {
            // empty class for testing
        }

        @Characterization()
        final class AnnnotationWithDefault {
            // empty class for testing
        }

        @Characterization("Example reason")
        final class AnnnotationWithDefaultSpecified {
            // empty class for testing
        }

        @Characterization(value = "Example reason")
        final class AnnnotationWithValueSpecifiedExplicitly {
            // empty class for testing
        }
    }


}
