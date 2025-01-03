package io.accelerate.challenge.definition.schema.types;

import com.fasterxml.jackson.databind.JsonNode;
import io.accelerate.challenge.definition.schema.TypeDefinition;

import java.util.List;

public enum PrimitiveType implements TypeDefinition {
    STRING("string", List.of(String.class)), 
    INTEGER("integer", List.of(Integer.class, int.class)), 
    BOOLEAN("boolean", List.of(Boolean.class, boolean.class));

    private final String displayName;
    private final List<Class<?>> compatibleClasses;

    PrimitiveType(String displayName, List<Class<?>> compatibleClasses) {
        this.displayName = displayName;
        this.compatibleClasses = compatibleClasses;
    }
    
    public static PrimitiveType fromDisplayName(String displayName) {
        for (PrimitiveType type : values()) {
            if (type.displayName.equals(displayName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant found for " + displayName);
    }

    public static PrimitiveType fromReferencedClass(Class<?> referencedClass) {
        for (PrimitiveType type : values()) {
            if (type.compatibleClasses.contains(referencedClass)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant found for " + referencedClass);
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public boolean isCompatible(JsonNode jsonNode) {
        switch (this) {
            case STRING -> {
                return jsonNode.isTextual();
            }
            case INTEGER -> {
                return jsonNode.isIntegralNumber();
            }
            case BOOLEAN -> {
                return jsonNode.isBoolean();
            }
            default -> throw new IllegalArgumentException("Unknown primitive type: " + this);
        }
    }
}
