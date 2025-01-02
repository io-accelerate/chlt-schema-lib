package io.accelerate.challenge.definition.schema.types;

import io.accelerate.challenge.definition.schema.TypeDefinition;

public record FieldDefinition(String fieldName, TypeDefinition typeDefinition) {
}
