`icm-bits-java`
===============

[![Build Status](https://travis-ci.org/ayeseeem/icm-bits-java.svg?branch=master)](https://travis-ci.org/ayeseeem/icm-bits-java)

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

Now with Java 9+ you can now use `List.of` and `Set.of`, so perhaps some of these
are no longer needed. But note that the details of mutability and modifiability
might be different.

There are also modifiable equivalents, `modifiableListOf()` and
`modifiableSetOf()`.

(See
[`ListSupport.java`](https://github.com/ayeseeem/icm-bits-java/blob/master/src/main/java/org/ayeseeem/say/java/util/ListSupport.java),
[`SetSupport.java`](https://github.com/ayeseeem/icm-bits-java/blob/master/src/main/java/org/ayeseeem/say/java/util/SetSupport.java),
[`ListSupportExamplesTest.java`](https://github.com/ayeseeem/icm-bits-java/blob/master/src/test/java/org/ayeseeem/say/example/ListSupportExamplesTest.java), and
[`SetSupportExamplesTest.java`](https://github.com/ayeseeem/icm-bits-java/blob/master/src/test/java/org/ayeseeem/say/example/SetSupportExamplesTest.java)
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
[`MapSupport.java`](https://github.com/ayeseeem/icm-bits-java/blob/master/src/main/java/org/ayeseeem/say/java/util/MapSupport.java) and
[`MapSupportExamplesTest.java`](https://github.com/ayeseeem/icm-bits-java/blob/master/src/test/java/org/ayeseeem/say/example/MapSupportExamplesTest.java)
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

Works for a `Collection`, an array of objects, or individual items listed
directly (using varargs).
See
[`ContainedItem.java`](https://github.com/ayeseeem/icm-bits-java/blob/master/src/main/java/org/ayeseeem/say/ContainedItem.java)
and
[`ContainedItemExamplesTest.java`](https://github.com/ayeseeem/icm-bits-java/blob/master/src/test/java/org/ayeseeem/say/example/ContainedItemExamplesTest.java)
for more details.


`@Characterization` annotation for JUnit `@Test`s
-------------------------------------------------

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
[`Characterization.java`](https://github.com/ayeseeem/icm-bits-java/blob/master/src/main/java/org/ayeseeem/test/Characterization.java) for more details.


Coding Standard
---------------

Basic standard is [icm-java-style](https://github.com/ayeseeem/icm-java-style/).
