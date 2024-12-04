package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChallengeTest {

    @Test
    void shouldSerializeAndDeserialize() throws Exception {
        // Create ObjectMapper for YAML
        ObjectMapper mapper = new ObjectMapper();

        // Create test challenge object
        Challenge challenge = new Challenge(
                "TST",
                1,
                "Test Challenge",
                List.of()
        );

        // Serialize to YAML
        String serialised = mapper.writeValueAsString(challenge);
        System.out.println(serialised);

        // Deserialize back to object
        Challenge deserializedChallenge = mapper.readValue(serialised, Challenge.class);

        // Assertions
        assertNotNull(deserializedChallenge);
        assertEquals("Test Challenge", deserializedChallenge.getName());
    }
}