package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.accelerate.challenge.definition.schema.types.FieldDefinition;
import io.accelerate.challenge.definition.schema.types.ListType;
import io.accelerate.challenge.definition.schema.types.ObjectType;
import io.accelerate.challenge.definition.schema.types.PrimitiveTypes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.accelerate.challenge.definition.utils.AssertionUtils.assertDeserializesToIdenticalObject;
import static io.accelerate.challenge.definition.utils.AssertionUtils.assertSerializesTo;

class ParamDefinitionTest {

    @Test
    void primitiveString() throws JsonProcessingException {
        ParamDefinition paramDefinition = new ParamDefinition("some name", 
                PrimitiveTypes.STRING);

        assertSerializesTo("""
                {
                  "description" : "some name",
                  "type" : "string"
                }
                """, paramDefinition);
        assertDeserializesToIdenticalObject(paramDefinition, paramDefinition.getClass());
    }

    @Test
    void primitiveInteger() throws JsonProcessingException {
        ParamDefinition paramDefinition = new ParamDefinition("some name",
                PrimitiveTypes.INTEGER);

        assertSerializesTo("""
                {
                  "description" : "some name",
                  "type" : "integer"
                }
                """, paramDefinition);
        assertDeserializesToIdenticalObject(paramDefinition, paramDefinition.getClass());
    }

    @Test
    void primitiveBoolean() throws JsonProcessingException {
        ParamDefinition paramDefinition = new ParamDefinition("some name",
                PrimitiveTypes.BOOLEAN);

        assertSerializesTo("""
                {
                  "description" : "some name",
                  "type" : "boolean"
                }
                """, paramDefinition);
        assertDeserializesToIdenticalObject(paramDefinition, paramDefinition.getClass());
    }
    
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~  Lists  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void listOfStrings() throws JsonProcessingException {
        ParamDefinition paramDefinition = new ParamDefinition("some name",
                new ListType(PrimitiveTypes.STRING));

        assertSerializesTo("""
                {
                  "description" : "some name",
                  "type" : "list(string)"
                }
                """, paramDefinition);
        assertDeserializesToIdenticalObject(paramDefinition, paramDefinition.getClass());
    }

    @Test
    void listOfIntegers() throws JsonProcessingException {
        ParamDefinition paramDefinition = new ParamDefinition("some name",
                new ListType(PrimitiveTypes.INTEGER));

        assertSerializesTo("""
                {
                  "description" : "some name",
                  "type" : "list(integer)"
                }
                """, paramDefinition);
        assertDeserializesToIdenticalObject(paramDefinition, paramDefinition.getClass());
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~  Objects  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    void complexObjectBuildByHand() throws JsonProcessingException {
        ParamDefinition paramDefinition = new ParamDefinition("some name",
                new ObjectType(List.of(
                        new FieldDefinition("someField", PrimitiveTypes.INTEGER),
                        new FieldDefinition("otherField", PrimitiveTypes.STRING)
                ))
        );

        assertSerializesTo("""
                {
                  "description" : "some name",
                   "type" : "object({someField=integer,otherField=string})"
                }
                """, paramDefinition);
        assertDeserializesToIdenticalObject(paramDefinition, paramDefinition.getClass());
    }
    
    record SomeObject(Integer someField, String otherField) {}

    @Test
    void complexObjectFromRecordClass() throws JsonProcessingException {
        ParamDefinition paramDefinition = new ParamDefinition("some name",
                ObjectType.from(SomeObject.class)
        );

        assertSerializesTo("""
                {
                  "description" : "some name",
                  "type" : "object({someField=integer,otherField=string})"
                }
                """, paramDefinition);
        assertDeserializesToIdenticalObject(paramDefinition, paramDefinition.getClass());
    }
    
    static class SomeFieldClass {
        public int someField;
        public String otherField;
    }

    @Test
    void complexObjectFromPlainClass() throws JsonProcessingException {
        ParamDefinition paramDefinition = new ParamDefinition("some name",
                ObjectType.from(SomeFieldClass.class)
        );

        assertSerializesTo("""
                {
                  "description" : "some name",
                  "type" : "object({someField=integer,otherField=string})"
                }
                """, paramDefinition);
        assertDeserializesToIdenticalObject(paramDefinition, paramDefinition.getClass());
    }
}