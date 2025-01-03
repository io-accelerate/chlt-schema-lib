package io.accelerate.challenge.definition.schema.types;

import org.junit.jupiter.api.Test;

import java.util.List;

import static io.accelerate.challenge.definition.utils.JsonUtils.asJsonNode;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ListTypeTest {

    @Test
    void displayName() {
        ListType listType = new ListType(PrimitiveType.INTEGER);
        
        assertThat(listType.getDisplayName(), equalTo("list(integer)"));
    }

    @Test
    void isCompatibleWithListOfMatchingType() {
        ListType listType = new ListType(PrimitiveType.INTEGER);
        
        assertThat(listType.isCompatible(asJsonNode(List.of(1, 2))), equalTo(true));
    }

    @Test
    void isNotCompatibleWithPrimitiveType() {
        ListType listType = new ListType(PrimitiveType.INTEGER);

        assertThat(listType.isCompatible(asJsonNode(3)), equalTo(false));
    }

    @Test
    void isNotCompatibleWithListOfOtherType() {
        ListType listType = new ListType(PrimitiveType.INTEGER);

        assertThat(listType.isCompatible(asJsonNode(List.of("x", "y"))), equalTo(false));
    }

    @Test
    void isNotCompatibleWithNullType() {
        ListType listType = new ListType(PrimitiveType.INTEGER);

        assertThat(listType.isCompatible(asJsonNode(null)), equalTo(false));
    }
}