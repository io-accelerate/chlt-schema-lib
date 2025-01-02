package io.accelerate.challenge.definition.schema.types;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.accelerate.challenge.definition.utils.JsonUtils.asJsonNode;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ObjectTypeTest {
    ObjectType objectType;

    @BeforeEach
    void setUp() {
        objectType = new ObjectType(
                List.of(new FieldDefinition("fieldInteger", PrimitiveTypes.INTEGER),
                        new FieldDefinition("fieldString", PrimitiveTypes.STRING)
                ));
    }

    @Test
    void displayName() {
        assertThat(objectType.getDisplayName(), equalTo("object({fieldInteger=integer,fieldString=string})"));
    }


    record SomeObject(Integer fieldInteger, String fieldString) {}

    @Test
    void fromRecordClass() {
        assertThat(ObjectType.from(SomeObject.class), equalTo(objectType));
    }

    static class SomeFieldClass {
        public int fieldInteger;
        public String fieldString;
    }

    @Test
    void fromPlainClass() {
        assertThat(ObjectType.from(SomeFieldClass.class), equalTo(objectType));
    }

    @Test
    void isCompatibleWithMatchingObject() {
        assertThat(objectType.isCompatible(asJsonNode(new SomeObject(1, "text"))), equalTo(true));
    }

    @Test
    void isNotCompatibleWithMapType() {
        assertThat(objectType.isCompatible(asJsonNode(Map.of("X", 1))), equalTo(false));
    }
    
    @Test
    void isNotCompatibleWithPrimitiveType() {
        assertThat(objectType.isCompatible(asJsonNode(3)), equalTo(false));
    }

    @Test
    void isNotCompatibleWithListType() {
        assertThat(objectType.isCompatible(asJsonNode(List.of("x", "y"))), equalTo(false));
    }
    
    @Test
    void isNotCompatibleWithNull() {
        assertThat(objectType.isCompatible(asJsonNode(null)), equalTo(false));
    }
}