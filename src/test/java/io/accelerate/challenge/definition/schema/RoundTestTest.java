package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;

class RoundTestTest {

    @Test
    void shouldSerializeAndDeserialize() throws Exception {
        RoundTest roundTest = new RoundTest(
                "TST_R1_T1",
                new MethodCall("someMethod",  List.of(1, 2, 3)),
                new RoundTestAssertionEquals(1)
        );

        ObjectMapper mapper = new ObjectMapper();
        String serialised = mapper.writeValueAsString(roundTest);
        RoundTest deserializedChallenge = mapper.readValue(serialised, RoundTest.class);

        assertNotNull(deserializedChallenge);
        assertThat(deserializedChallenge, samePropertyValuesAs(roundTest));
    }
}