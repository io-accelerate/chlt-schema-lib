package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.databind.JsonNode;

public interface TypeDefinition {
    String getDisplayName();
    boolean isCompatible(JsonNode jsonNode);
}
