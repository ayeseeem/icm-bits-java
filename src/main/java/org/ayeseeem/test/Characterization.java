package org.ayeseeem.test;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

/**
 * Marks unit tests (whole test classes, or individual test methods) as
 * characterization tests. That is, tests that <em>characterize</em> (capture,
 * or describe, or document) the behaviour of the code, but without necessarily
 * <em>specifying</em> the behaviour. For example,
 *
 * <pre>
 * &#64;Characterization
 * &#64;Test
 * public void testDefaultResponseIsNull() {
 *     // ...
 * }
 * </pre>
 *
 * is a test that <em>describes</em> the current behaviour, but is <em>not</em>
 * a specification or requirement.
 * <p>
 * A <em>reason</em> can be supplied via the {@link #value()}:
 *
 * <pre>
 * &#64;Characterization("Currently null not empty - don't know why")
 * &#64;Test
 * public void testDefaultResponse() {
 *     assertThat(defaultResponse, is(nullValue()));
 * }
 * </pre>
 *
 * <b>Note</b> the preferred order - {@code @Characterization}, then
 * {@code @Test}. This ordering is not required, but it reads better: it is a
 * "characterization test", it doesn't "test (the) characterization".
 * <p>
 * The main reason we need this annotation is that we do not name our normal
 * tests with "should": it is usually implied. So the example above -
 * {@code testDefaultResponseIsNull} would normally be read as "default response
 * should be null". By marking it with this annotation, we are indicating that
 * the default response <em>is</em> null, but without saying that it
 * <em>should</em> be.
 * <p>
 * {@code @Characterization} is a {@code @Documented} annotation. This has no
 * particular effect at the moment, but one can imagine a custom test-runner
 * could use this annotation to report failed {@code @Characterization @Test}s
 * differently to unannotated tests - for example, explaining that captured
 * behaviour has changed.
 */
@Documented
@Target({ TYPE, METHOD })
public @interface Characterization {

    /**
     * Optional reason, clarification, or explanation of the test, or of the current
     * behaviour. Currently specified by the {@code value} attribute, so that it
     * does not need to be specified explicitly, so you can write:
     *
     * <pre>
     * &#64;Characterization("Some reason")
     * </pre>
     *
     * instead of
     *
     * <pre>
     * &#64;Characterization(value = "Some reason")
     * </pre>
     *
     * @return the value/reason
     */
    String value() default "";
}
