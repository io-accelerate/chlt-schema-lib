package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.accelerate.challenge.definition.schema.types.FieldDefinition;
import io.accelerate.challenge.definition.schema.types.ListType;
import io.accelerate.challenge.definition.schema.types.ObjectType;
import io.accelerate.challenge.definition.schema.types.PrimitiveType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.accelerate.challenge.definition.utils.AssertionUtils.assertDeserializesToIdenticalObject;
import static io.accelerate.challenge.definition.utils.AssertionUtils.assertSerializesTo;

class ParamDefinitionTest {

    @Test
    void primitiveType() throws JsonProcessingException {
        ParamDefinition paramDefinition = new ParamDefinition("some name", 
                PrimitiveType.STRING);

        assertSerializesTo("""
                {
                  "description" : "some name",
                  "type" : "string"
                }
                """, paramDefinition);
        assertDeserializesToIdenticalObject(paramDefinition, paramDefinition.getClass());
    }
    
    @Test
    void listType() throws JsonProcessingException {
        ParamDefinition paramDefinition = new ParamDefinition("some name",
                new ListType(PrimitiveType.STRING));

        assertSerializesTo("""
                {
                  "description" : "some name",
                  "type" : "list(string)"
                }
                """, paramDefinition);
        assertDeserializesToIdenticalObject(paramDefinition, paramDefinition.getClass());
    }

    @Test
    void complexObjectBuildByHand() throws JsonProcessingException {
        ParamDefinition paramDefinition = new ParamDefinition("some name",
                new ObjectType(List.of(
                        new FieldDefinition("someField", PrimitiveType.INTEGER),
                        new FieldDefinition("otherField", PrimitiveType.STRING)
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
}