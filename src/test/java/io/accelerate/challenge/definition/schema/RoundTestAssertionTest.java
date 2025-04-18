package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.core.JsonProcessingException;
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
    void shouldSerializeAndDeserializeWithNullEquals() throws JsonProcessingException {
        RoundTestAssertion object = new RoundTestAssertion(RoundTestAssertionType.EQUALS, null);

        assertSerializesTo("""
                {
                  "equals" : null
                }
                """, object);
        assertDeserializesToIdenticalObject(object, object.getClass());
    }

    @Test
    void shouldSerializeAndDeserializeWithContains() throws JsonProcessingException {
        RoundTestAssertion object = new RoundTestAssertion(RoundTestAssertionType.CONTAINS_STRING, "some_value");

        assertSerializesTo("""
                {
                  "containsString" : "some_value"
                }
                """, object);
        assertDeserializesToIdenticalObject(object, object.getClass());
    }

    @Test
    void shouldSerializeAndDeserializeWithContainsIgnoringCase() throws JsonProcessingException {
        RoundTestAssertion object = new RoundTestAssertion(RoundTestAssertionType.CONTAINS_STRING_IGNORING_CASE, "some_value");

        assertSerializesTo("""
                {
                  "containsStringIgnoringCase" : "some_value"
                }
                """, object);
        assertDeserializesToIdenticalObject(object, object.getClass());
    }

    @Test
    void shouldSerializeAndDeserializeWithMultilineStringEquals() throws JsonProcessingException {
        RoundTestAssertion object = new RoundTestAssertion(RoundTestAssertionType.MULTILINE_STRING_EQUALS, "some_value");

        assertSerializesTo("""
                {
                  "multilineStringEquals" : "some_value"
                }
                """, object);
        assertDeserializesToIdenticalObject(object, object.getClass());
    }

    @Test
    void shouldSerializeAndDeserializeWithNullCheck() throws JsonProcessingException {
        RoundTestAssertion object = new RoundTestAssertion(RoundTestAssertionType.IS_NULL, true);

        assertSerializesTo("""
                {
                  "isNull" : true
                }
                """, object);
        assertDeserializesToIdenticalObject(object, object.getClass());
    }
}