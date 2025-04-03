package io.accelerate.challenge.definition.schema.types;

import com.fasterxml.jackson.databind.JsonNode;
import io.accelerate.challenge.definition.schema.TypeDefinition;

import java.util.Iterator;
import java.util.Objects;

public class MapType implements TypeDefinition {
    private final TypeDefinition mapValueType;

    public MapType(TypeDefinition mapValueType) {
        this.mapValueType = mapValueType;
    }

    @Override
    public String getDisplayName() {
        return "map(" + mapValueType.getDisplayName() + ")";
    }

    @Override
    public boolean isCompatible(JsonNode jsonNode) {
        boolean isCompatible;
        if (jsonNode.isObject()) {
            isCompatible = true;
            Iterator<String> stringIterator = jsonNode.fieldNames();
            while (stringIterator.hasNext()) {
                String fieldName = stringIterator.next();
                if (!mapValueType.isCompatible(jsonNode.get(fieldName))) {
                    isCompatible = false;
                }
            }
        } else {
            isCompatible = false;
        }
        return isCompatible;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MapType listType = (MapType) o;
        return Objects.equals(mapValueType, listType.mapValueType);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mapValueType);
    }
}
