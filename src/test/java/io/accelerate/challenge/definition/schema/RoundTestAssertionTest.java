package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static io.accelerate.challenge.definition.utils.AssertionUtils.assertDeserializesToIdenticalObject;
import static io.accelerate.challenge.definition.utils.AssertionUtils.assertSerializesTo;

class RoundTestAssertionTest {

    @Test
    void shouldSerializeAndDeserializeWithEquals() throws JsonProcessingException {
        RoundTestAssertion object = new RoundTestAssertion(RoundTestAssertionType.EQUALS, 1);

        assertSerializesTo("""
                {
                  "equals" : 1
                }
                """, object);
        assertDeserializesToIdenticalObject(object, object.getClass());
    }

    @Test
    void shouldSerializeAndDeserializeWithContains() throws JsonProcessingException {
        RoundTestAssertion object = new RoundTestAssertion(RoundTestAssertionType.CONTAINS, "some_value");

        assertSerializesTo("""
                {
                  "contains" : "some_value"
                }
                """, object);
        assertDeserializesToIdenticalObject(object, object.getClass());
    }
}