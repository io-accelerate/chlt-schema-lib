package io.accelerate.challenge.definition.schema.types;

import com.fasterxml.jackson.databind.JsonNode;
import io.accelerate.challenge.definition.schema.TypeDefinition;

import java.util.Objects;
import java.util.stream.StreamSupport;

public class ListType implements TypeDefinition {
    private final TypeDefinition objectType;

    public ListType(TypeDefinition objectType) {
        this.objectType = objectType;
    }

    @Override
    public String getDisplayName() {
        return "list(" + objectType.getDisplayName() + ")";
    }

    @Override
    public boolean isCompatible(JsonNode jsonNode) {
        if (jsonNode.isArray()) {
            return StreamSupport.stream(jsonNode.spliterator(), false)
                    .allMatch(objectType::isCompatible);
        } else {
            return false;
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ListType listType = (ListType) o;
        return Objects.equals(objectType, listType.objectType);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(objectType);
    }
}
