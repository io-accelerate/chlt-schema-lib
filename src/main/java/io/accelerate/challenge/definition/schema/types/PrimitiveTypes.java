package io.accelerate.challenge.definition.schema.types;

import com.fasterxml.jackson.databind.JsonNode;
import io.accelerate.challenge.definition.schema.TypeDefinition;

import java.util.List;

public enum PrimitiveTypes implements TypeDefinition {
    STRING("string", List.of(String.class)), 
    INTEGER("integer", List.of(Integer.class, int.class)), 
    BOOLEAN("boolean", List.of(Boolean.class, boolean.class));

    private final String displayName;
    private final List<Class<?>> compatibleClasses;

    PrimitiveTypes(String displayName, List<Class<?>> compatibleClasses) {
        this.displayName = displayName;
        this.compatibleClasses = compatibleClasses;
    }
    
    public static PrimitiveTypes fromDisplayName(String displayName) {
        for (PrimitiveTypes type : values()) {
            if (type.displayName.equals(displayName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant found for " + displayName);
    }

    public static PrimitiveTypes fromReferencedClass(Class<?> referencedClass) {
        for (PrimitiveTypes type : values()) {
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
