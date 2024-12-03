package io.accelerate.challenge.definition.validate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.erosb.jsonsKema.*;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;

public class ChallengeDefinitionValidator {
    public static final String SCHEMA_URL = "https://get.accelerate.io/challenge-toolkit/schema/version/0.1.4/schema.yaml";
    private static Validator validator;
    private final ObjectMapper objectMapper;
    private final ObjectMapper jsonMapper;

    public ChallengeDefinitionValidator() {
        objectMapper = new ObjectMapper(new YAMLFactory());
        jsonMapper = new ObjectMapper();

        Schema schema = SchemaLoader.forURL(SCHEMA_URL).load();
        validator = Validator.create(schema, new ValidatorConfig(FormatValidationPolicy.ALWAYS));
    }

    public ValidationResult validate(Path yamlFilePath) throws IOException {
        Object yamlData = objectMapper.readValue(yamlFilePath.toFile(), Object.class);
        String yamlFileAsJson = jsonMapper.writeValueAsString(yamlData);
        ValidationFailure maybeValidationFailure = validator.validate(yamlFileAsJson, URI.create("mem://local-loading"));
        return new ValidationResult(maybeValidationFailure);
    }




}
