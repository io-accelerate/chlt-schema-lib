package io.accelerate.challenge.definition.loader;

import io.accelerate.challenge.definition.schema.*;
import io.accelerate.challenge.definition.validator.ChallengeDefinitionValidator;
import io.accelerate.challenge.definition.validator.ValidationResult;
import io.accelerate.challenge.definition.writer.ChallengeDefinitionWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;


class ChallengeDefinitionAcceptanceTest {

    @TempDir
    Path tempDir;

    @Test
    void shouldWriteValidateAndParse() throws Exception {
        // Create a challenge
        ChallengeBase originalChallenge = new ChallengeBase(
                "TST",
                1,
                "A test challenge",
                List.of(new ChallengeRoundBase(
                        "TST_R1",
                        "A test round",
                        MethodDefinitions.of(new MethodDefinition("someMethod", List.of(String.class), Integer.class)),
                        List.of(
                                new RoundTest("TST_R1_1",
                                        new MethodCall("someMethod", List.of("foo")),
                                        new RoundTestAssertionEquals(123)),
                                new RoundTest("TST_R1_2",
                                        new MethodCall("someMethod", List.of("bar")),
                                        new RoundTestAssertionEquals(456))
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
        ChallengeBase loadedChallenge = loader.fromFile(writtenChallengeFilePath);

        // Compare with original
        assertThat(loadedChallenge, samePropertyValuesAs(originalChallenge));
    }}
