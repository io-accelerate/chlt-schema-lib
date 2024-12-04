package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;

class ChallengeRoundTest {

    @Test
    void shouldSerializeAndDeserialize() throws Exception {

        ChallengeRound challengeRound = new ChallengeRound(
                "TST_R1",
                "A test round",
                MethodDefinitions.of(new MethodDefinition("someMethod", List.of(String.class), Integer.class)),
                List.of()
        );

        ObjectMapper mapper = new ObjectMapper();
        String serialised = mapper.writeValueAsString(challengeRound);
        ChallengeRound deserializedChallenge = mapper.readValue(serialised, ChallengeRound.class);

        assertNotNull(deserializedChallenge);
        assertThat(deserializedChallenge, samePropertyValuesAs(challengeRound));
    }
}