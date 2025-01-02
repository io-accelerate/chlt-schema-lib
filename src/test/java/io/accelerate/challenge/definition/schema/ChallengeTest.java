package io.accelerate.challenge.definition.schema;

import org.junit.jupiter.api.Test;

import java.util.List;

import static io.accelerate.challenge.definition.utils.AssertionUtils.assertDeserializesToIdenticalObject;
import static io.accelerate.challenge.definition.utils.AssertionUtils.assertSerializesTo;

class ChallengeTest {

    @Test
    void shouldSerializeAndDeserialize() throws Exception {
        Challenge challenge = new Challenge(
                "TST",
                1,
                "Test Challenge",
                List.of()
        );

        assertSerializesTo("""
                {
                  "$schema" : "https://get.accelerate.io/challenge-toolkit/schema/version/0.2.1/schema.yaml",
                  "id" : "TST",
                  "version" : 1,
                  "name" : "Test Challenge",
                  "rounds" : [ ]
                }
                """, challenge);
        assertDeserializesToIdenticalObject(challenge, challenge.getClass());
    }
}