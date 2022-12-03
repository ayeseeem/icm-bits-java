`icm-bits-java`
===============

[![Build Status](https://travis-ci.com/ayeseeem/icm-bits-java.svg?branch=master)](https://travis-ci.com/github/ayeseeem/icm-bits-java)

Expressive Java utilities from [ayeseeem.org](https://www.ayeseeem.org/).
Designed to make code clearer.


Expressive `Collection` factory methods
------------------------------------------

### Nicer ways to create simple `List`s and `Set`s ###

Use static imports and you can write like this:

```java
List<Month> latinNumberedPeriod = listOf(SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER);

Set<Month> latinNumberedMonths = setOf(SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER);
```

Use `listOf` instead of `Arrays.asList`. There was no equivalent for
`setOf`.

If you want to emphasize the nature of the collection, you can use
`unmodifiableListOf()` and `unmodifiableSetOf()`.
These are like Java's `Collections.unmodifiableList()`, and
`Collections.unmodifiableSet()`, but they take varargs, not a collection.
There are modifiable equivalents: `modifiableListOf()` and `modifiableSetOf()`.

If you want an empty list, use `emptyList()`, which is clearer than writing
`listOf()` with no arguments.
If modifiability is significant, use `initiallyEmptyList()` or
`alwaysEmptyList()`.
There are set equivalents: `emptySet()`, `initiallyEmptySet()`, and
`alwaysEmptySet()`.

Now with Java 9+ you can now use `List.of` and `Set.of`, so perhaps some of these
are no longer needed. But note that the details of mutability and modifiability
might be different.

(See
[`ListSupport.java`](src/main/java/org/ayeseeem/say/java/util/ListSupport.java),
[`SetSupport.java`](src/main/java/org/ayeseeem/say/java/util/SetSupport.java),
[`ListSupportExamplesTest.java`](src/test/java/org/ayeseeem/say/example/ListSupportExamplesTest.java),
and
[`SetSupportExamplesTest.java`](src/test/java/org/ayeseeem/say/example/SetSupportExamplesTest.java)
for more details.
)


### Explain your choice of `Map` ###

Clearer ways of expressing why you have chosen a particular `Map`
*implementation*. Which expresses more clearly and directly what is being done?:

```java
supportedCodes = keySortedMap();
latestCodes = insertionOrderedMap();
```

```java
supportedCodes = new TreeMap<>(); // key-sorted
latestCodes = new LinkedHashMap<>(); // insertion-ordered
```

(See
[`MapSupport.java`](src/main/java/org/ayeseeem/say/java/util/MapSupport.java)
and
[`MapSupportExamplesTest.java`](src/test/java/org/ayeseeem/say/example/MapSupportExamplesTest.java)
for more details.
)


`ContainedItem` for better `if`s
--------------------------------

Allows clearer, more expressive `if` statements testing whether an item is in a
group of items. Which of these do you prefer? Which expresses more clearly
and directly what is being asked?:

```java
if (the(month).isOneOf(SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER) {
    // its name derives from its Latin ordinal in the Roman calendar
}

if (Arrays.asList(SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER).contains(month)) {
    // ...
}
```

```java
if (the(month).isIn(myFavouriteMonths) {
    // ...
}

if (myFavouriteMonths.contains(month)) {
    // ...
}
```

Note that this can also be used to avoid certain types of errors that are hard
to spot, often caused by copy-and-paste with repeated tests in long `if`
statements. For example, you can replace the (broken)

```java
if (longComplicatedlyNamedThing1 == 1 || longComplicatedlyNamedThing2 == 3 || longComplicatedlyNamedThing1 == 7)
```

with the less error-prone

```java
if (the(longComplicatedlyNamedThing1).isOneOf(1, 3, 7))
```

Although this appears to sacrifice the "short-circuit" behaviour of `||`,
`List.contains()` presumably acts similarly, at least if the values are simple
values, not expressions to be evaluated.

Works for a `Collection`, an array of objects, or individual items listed
directly (using varargs).
See
[`ContainedItem.java`](src/main/java/org/ayeseeem/say/ContainedItem.java)
and
[`ContainedItemExamplesTest.java`](src/test/java/org/ayeseeem/say/example/ContainedItemExamplesTest.java)
for more details.


Help for Writing Characterization Tests
---------------------------------------

### `@Characterization` annotation for JUnit `@Test`s ###

Marks unit tests (whole test classes, or individual test methods) as
characterization tests. That is, tests that *characterize* (capture,
or describe, or document) the behaviour of the code, but without necessarily
*specifying* the behaviour. For example,

```java
 @Characterization
 @Test
 public void testDefaultResponseIsNull() {
     // ...
 }
```

See
[`Characterization.java`](src/main/java/org/ayeseeem/test/Characterization.java)
for more details.


### Document and Test Ideal and Current Behaviour ###

Write a characterization test that both shows what the current behaviour is,
*and* what it *should* be. If the behaviour is ever corrected, the test will
start failing, highlighting the fix, so you can consider the implications.

For example, instead of writing

```java
assertThat(findBooks("unknown author"),
        is(nullValue())); // should be empty
```

you can write

```java
assertThat(findBooks("unknown author"),
        ideally(is(empty()))
        .butCurrently(is(nullValue())));
```

The terms "ideally" and "currently" are chosen to be expressive, and to avoid
confusion with the terms "expected" and "actual" that are used in normal tests.

See
[`CharacterizationMatcher.java`](src/main/java/org/ayeseeem/test/CharacterizationMatcher.java)
and
[`CharacterizationMatcherExampleTest.java`](src/test/java/org/ayeseeem/test/example/CharacterizationMatcherExampleTest.java)
for more details.


TODOs
-----

- [ ] JUnit rules to help confirm time-related code, for example:
  - Use a random time zone every test.
    - Random is handy for developer tests, but...
    - Not great for Continuous Delivery or repeatable builds: not *great*
      to have randomly failing tests, especially if not possible to then
      diagnose: need a complete error message? That (easily) allows a re-run.
  - Run test with a range of time zones/offsets/DSTs, to be sure code does not
    rely upon current time zone.
  - For ideas, see
    [this Stackoverflow question](https://stackoverflow.com/questions/10846704/how-do-i-write-unit-tests-to-make-sure-my-date-time-based-code-works-for-all-tim).
  - Is there a way to write a helper that has no JUnit dependency, so our
    code is dependency free?
    Is it worth it - we use JUnit for our tests anyway, so live with it?


Coding Standard
---------------

Basic standard is [icm-java-style](https://github.com/ayeseeem/icm-java-style/).
