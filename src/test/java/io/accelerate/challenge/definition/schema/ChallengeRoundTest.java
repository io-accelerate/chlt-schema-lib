package io.accelerate.challenge.definition.schema;

import io.accelerate.challenge.definition.schema.types.PrimitiveType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.accelerate.challenge.definition.utils.AssertionUtils.assertDeserializesToIdenticalObject;
import static io.accelerate.challenge.definition.utils.AssertionUtils.assertSerializesTo;

class ChallengeRoundTest {

    @Test
    void shouldSerializeAndDeserialize() throws Exception {

        ChallengeRound challengeRound = new ChallengeRound(
                "TST_R1",
                "A test round",
                MethodDefinitions.of(new MethodDefinition("someMethod", 
                        List.of(new ParamDefinition("some input", PrimitiveType.STRING)), 
                        new ReturnDefinition("result", PrimitiveType.INTEGER))),
                List.of()
        );

        assertSerializesTo("""
                {
                  "id" : "TST_R1",
                  "description" : "A test round",
                  "methods" : [ {
                    "name" : "someMethod",
                    "params" : [ {
                      "description" : "some input",
                      "type" : "string"
                    } ],
                    "returns" : {
                      "description" : "result",
                      "type" : "integer"
                    }
                  } ],
                  "tests" : [ ]
                }
                """, challengeRound);

        assertDeserializesToIdenticalObject(challengeRound, challengeRound.getClass());
    }

    @Test
    void shouldTrimDescription() throws Exception {
        ChallengeRound challengeRound = new ChallengeRound(
                "TST_R1",
                "A test round  \n sdssfsdf   \n    ",
                new MethodDefinitions(),
                List.of()
        );
        assertSerializesTo("""
                {
                  "id" : "TST_R1",
                  "description" : "A test round\\n sdssfsdf\\n",
                  "methods" : [ ],
                  "tests" : [ ]
                }""", challengeRound);
    }

}