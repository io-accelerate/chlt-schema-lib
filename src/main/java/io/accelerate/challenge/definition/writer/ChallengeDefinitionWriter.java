package io.accelerate.challenge.definition.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import io.accelerate.challenge.definition.schema.Challenge;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// TODO: Move all to separate libs repo and publish to Maven Central
public class ChallengeDefinitionWriter {

    private final File destinationDirectory;

    public ChallengeDefinitionWriter(File destinationDirectory) {
        this.destinationDirectory = destinationDirectory;
    }

    public Path writeChallengeToFile(Challenge challenge) throws IOException {
        Path filePath = destinationDirectory.toPath()
                .resolve(challenge.getId())
                .resolve("v"+challenge.getVersion())
                .resolve("definition.yaml");
        ensureParentDirs(filePath);

        YAMLFactory jf = new YAMLFactory();
        jf.enable(YAMLGenerator.Feature.LITERAL_BLOCK_STYLE);

        ObjectMapper yamlMapper = new ObjectMapper(jf);

        // Write the YAML file
        ObjectWriter objectWriter = yamlMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(filePath.toFile(), challenge);

        // Return the file path
        return filePath;
    }

    public static void ensureParentDirs(Path path) throws IOException {
        Path parentDir = path.getParent(); // Get the parent directory

        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }
    }
}
