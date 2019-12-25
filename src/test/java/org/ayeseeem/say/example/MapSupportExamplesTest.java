package org.ayeseeem.say.example;

import static org.ayeseeem.say.java.util.MapSupport.insertionOrderedMap;
import static org.ayeseeem.say.java.util.MapSupport.keySortedMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;

import java.util.Map;

import org.junit.Test;

public class MapSupportExamplesTest {

    @Test
    public void testKeySortedMapExample_KeysAreSorted() {
        Map<Integer, String> supportedCodes = keySortedMap();
        supportedCodes.put(404, "Not Found");
        supportedCodes.put(500, "Internal Server Error");
        supportedCodes.put(200, "OK");

        assertThat(supportedCodes.keySet(), containsInAnyOrder(404, 500, 200));
        assertThat(supportedCodes.keySet(), contains(200, 404, 500));
    }

    @Test
    public void testInsertionOrderedMapExample_KeysAreInInsertionOrder() {
        Map<Integer, String> latestCodes = insertionOrderedMap();
        latestCodes.put(404, "Not Found");
        latestCodes.put(500, "Internal Server Error");
        latestCodes.put(200, "OK");

        assertThat(latestCodes.keySet(), containsInAnyOrder(404, 500, 200));
        assertThat(latestCodes.keySet(), contains(404, 500, 200));
    }

}
