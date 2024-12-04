package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChallengeTest {

    @Test
    void shouldSerializeAndDeserialize() throws Exception {
        Challenge challenge = new Challenge(
                "TST",
                1,
                "Test Challenge",
                List.of()
        );

        ObjectMapper mapper = new ObjectMapper();
        String serialised = mapper.writeValueAsString(challenge);
        Challenge deserializedChallenge = mapper.readValue(serialised, Challenge.class);

        assertNotNull(deserializedChallenge);
        assertThat(deserializedChallenge, samePropertyValuesAs(challenge));
    }
}