package io.accelerate.challenge.definition.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.accelerate.challenge.definition.schema.Challenge;

import java.io.IOException;
import java.nio.file.Path;

public class ChallengeDefinitionLoader {
    private final ObjectMapper objectMapper;

    public ChallengeDefinitionLoader() {
        objectMapper = new ObjectMapper(new YAMLFactory());
    }

    public Challenge fromFile(Path writtenChallengeFilePath) throws IOException {
        return objectMapper.readValue(writtenChallengeFilePath.toFile(), Challenge.class);
    }
}
