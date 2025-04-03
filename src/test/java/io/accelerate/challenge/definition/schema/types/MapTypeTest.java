package io.accelerate.challenge.definition.schema.types;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.accelerate.challenge.definition.utils.JsonUtils.asJsonNode;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class MapTypeTest {

    @Test
    void displayName() {
        MapType mapType = new MapType(PrimitiveType.INTEGER);
        
        assertThat(mapType.getDisplayName(), equalTo("map(integer)"));
    }

    @Test
    void isCompatibleWithMapOfMatchingType() {
        MapType mapType = new MapType(PrimitiveType.INTEGER);
        
        assertThat(mapType.isCompatible(asJsonNode(Map.of("key1", 2))), equalTo(true));
    }

    @Test
    void isNotCompatibleWithPrimitiveType() {
        MapType mapType = new MapType(PrimitiveType.INTEGER);

        assertThat(mapType.isCompatible(asJsonNode(3)), equalTo(false));
    }

    @Test
    void isNotCompatibleWithMapOfOtherType() {
        MapType mapType = new MapType(PrimitiveType.INTEGER);

        assertThat(mapType.isCompatible(asJsonNode(Map.of("key1", "y"))), equalTo(false));
    }

    @Test
    void isNotCompatibleWithAMixedMap() {
        MapType mapType = new MapType(PrimitiveType.INTEGER);

        assertThat(mapType.isCompatible(asJsonNode(Map.of("key1", 1, "key2", "text"))), equalTo(false));
    }

    @Test
    void isNotCompatibleWithNullType() {
        MapType mapType = new MapType(PrimitiveType.INTEGER);

        assertThat(mapType.isCompatible(asJsonNode(null)), equalTo(false));
    }
}