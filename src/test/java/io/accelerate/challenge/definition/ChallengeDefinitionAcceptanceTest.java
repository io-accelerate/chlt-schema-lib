package io.accelerate.challenge.definition;

import io.accelerate.challenge.definition.loader.ChallengeDefinitionLoader;
import io.accelerate.challenge.definition.schema.*;
import io.accelerate.challenge.definition.schema.types.PrimitiveTypes;
import io.accelerate.challenge.definition.validator.ChallengeDefinitionValidator;
import io.accelerate.challenge.definition.validator.ValidationResult;
import io.accelerate.challenge.definition.writer.ChallengeDefinitionWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class ChallengeDefinitionAcceptanceTest {

    @TempDir
    Path tempDir;

    @Test
    void shouldWriteValidateAndParse() throws Exception {
        // Create a challenge
        Challenge originalChallenge = new Challenge(
                "TST",
                1,
                "A test challenge",
                List.of(new ChallengeRound(
                        "TST_R1",
                        "A test round",
                        MethodDefinitions.of(new MethodDefinition("someMethod",
                                List.of(new ParamDefinition("some input", PrimitiveTypes.STRING)),
                                new ParamDefinition("result", PrimitiveTypes.INTEGER))),
                        List.of(
                                new RoundTest("TST_R1_1",
                                        new MethodCall("someMethod", List.of("foo")),
                                        new RoundTestAssertion(RoundTestAssertionType.EQUALS, 123)),
                                new RoundTest("TST_R1_2",
                                        new MethodCall("someMethod", List.of("bar")),
                                        new RoundTestAssertion(RoundTestAssertionType.EQUALS, 456))
                        ))
                )
        );

        // Serialize using ChallengeDefinitionWriter
        ChallengeDefinitionWriter writer = new ChallengeDefinitionWriter(tempDir.toFile());
        Path writtenChallengeFilePath = writer.writeChallengeToFile(originalChallenge);

        // Validate using ChallengeDefinitionValidator
        ChallengeDefinitionValidator validator = new ChallengeDefinitionValidator();
        ValidationResult validationResult = validator.validate(writtenChallengeFilePath);
        assertThat(validationResult.maybeValidationFailure(), is(org.hamcrest.Matchers.nullValue()));

        // Deserialize using ChallengeDefinitionLoader
        ChallengeDefinitionLoader loader = new ChallengeDefinitionLoader();
        Challenge loadedChallenge = loader.fromFile(writtenChallengeFilePath);

        // Compare with original
        assertThat(loadedChallenge, equalTo(originalChallenge));
    }}
