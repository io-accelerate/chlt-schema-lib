package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.accelerate.challenge.definition.utils.AssertionUtils.assertDeserializesToIdenticalObject;
import static io.accelerate.challenge.definition.utils.AssertionUtils.assertSerializesTo;

class RoundTestTest {

    @Test
    void shouldSerializeAndDeserialize() throws Exception {
        RoundTest roundTest = new RoundTest(
                "TST_R1_T1",
                new MethodCall("someMethod",  List.of(1, 2, 3)),
                new RoundTestAssertion(RoundTestAssertionType.EQUALS, 123)
        );

        assertSerializesTo("""
                {
                  "id" : "TST_R1_T1",
                  "call" : {
                    "method" : "someMethod",
                    "args" : [ 1, 2, 3 ]
                  },
                  "expect" : {
                    "equals" : 123
                  }
                }
                """, roundTest);
        assertDeserializesToIdenticalObject(roundTest, roundTest.getClass());
    }
}