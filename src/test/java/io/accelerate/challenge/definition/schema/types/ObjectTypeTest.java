package io.accelerate.challenge.definition.schema.types;

import org.junit.jupiter.api.Test;

import java.util.List;

import static io.accelerate.challenge.definition.utils.JsonUtils.asJsonNode;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ObjectTypeTest {

    @Test
    void displayName() {
        ObjectType objectType = new ObjectType(
                List.of(new FieldDefinition("fieldInteger", PrimitiveTypes.INTEGER), 
                        new FieldDefinition("fieldString", PrimitiveTypes.STRING)
                ));

        assertThat(objectType.getDisplayName(), equalTo("object({fieldInteger=integer,fieldString=string})"));
    }


    record SomeObject(Integer fieldInteger, String fieldString) {}

    @Test
    void fromRecordClass() {
        ObjectType expectedObjectType = new ObjectType(
                List.of(new FieldDefinition("fieldInteger", PrimitiveTypes.INTEGER),
                        new FieldDefinition("fieldString", PrimitiveTypes.STRING)
                ));
        assertThat(ObjectType.from(SomeObject.class), equalTo(expectedObjectType));
    }

    static class SomeFieldClass {
        public int fieldInteger;
        public String fieldString;
    }

    @Test
    void fromPlainClass() {
        ObjectType expectedObjectType = new ObjectType(
                List.of(new FieldDefinition("fieldInteger", PrimitiveTypes.INTEGER),
                        new FieldDefinition("fieldString", PrimitiveTypes.STRING)
                ));
        assertThat(ObjectType.from(SomeFieldClass.class), equalTo(expectedObjectType));
    }

    @Test
    void isCompatibleWithMatchingObject() {
        ObjectType objectType = new ObjectType(
                List.of(new FieldDefinition("fieldInteger", PrimitiveTypes.INTEGER),
                        new FieldDefinition("fieldString", PrimitiveTypes.STRING)
                ));
        
        assertThat(objectType.isCompatible(asJsonNode(new SomeObject(1, "text"))), equalTo(true));
    }

    @Test
    void isNotCompatibleWithPrimitiveType() {
        ListType listType = new ListType(PrimitiveTypes.INTEGER);

        assertThat(listType.isCompatible(asJsonNode(3)), equalTo(false));
    }

    @Test
    void isNotCompatibleWithListType() {
        ListType listType = new ListType(PrimitiveTypes.INTEGER);

        assertThat(listType.isCompatible(asJsonNode(List.of("x", "y"))), equalTo(false));
    }
    
}