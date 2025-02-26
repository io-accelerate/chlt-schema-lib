package io.accelerate.challenge.definition.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.accelerate.challenge.definition.schema.Challenge;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

public class ChallengeDefinitionLoader {
    private final ObjectMapper objectMapper;

    public ChallengeDefinitionLoader() {
        objectMapper = new ObjectMapper(new YAMLFactory());
    }

    public Challenge fromFile(Path challengeFilePath) throws IOException {
        return objectMapper.readValue(challengeFilePath.toFile(), Challenge.class);
    }

    public Challenge fromURL(URL challengeURL) throws IOException {
        return objectMapper.readValue(challengeURL, Challenge.class);
    }
}
