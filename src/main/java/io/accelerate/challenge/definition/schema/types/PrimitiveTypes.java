package io.accelerate.challenge.definition.schema.types;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.accelerate.challenge.definition.schema.TypeDefinition;

public enum PrimitiveTypes implements TypeDefinition {
    STRING("string", String.class), 
    INTEGER("integer", Integer.class), 
    BOOLEAN("boolean", Boolean.class);

    private final String displayName;
    private final Class<?> referencedClass;

    PrimitiveTypes(String displayName, Class<?> referencedClass) {
        this.displayName = displayName;
        this.referencedClass = referencedClass;
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
            if (type.referencedClass.equals(referencedClass)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant found for " + referencedClass);
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}
