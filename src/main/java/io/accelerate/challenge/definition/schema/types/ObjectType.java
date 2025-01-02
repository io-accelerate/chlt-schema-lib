package io.accelerate.challenge.definition.schema.types;

import com.fasterxml.jackson.databind.JsonNode;
import io.accelerate.challenge.definition.schema.TypeDefinition;

import java.lang.reflect.Field;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ObjectType implements TypeDefinition {
    private final List<FieldDefinition> objectFields;

    public ObjectType(List<FieldDefinition> objectFields) {
        this.objectFields = objectFields;
    }
    
    public static ObjectType from(Class<?> referencedClass) {
        List<FieldDefinition> fields;
        if (referencedClass.isRecord()) {
            RecordComponent[] recordComponents = referencedClass.getRecordComponents();
            fields = Arrays.stream(recordComponents)
                    .map(recordComponent -> new FieldDefinition(recordComponent.getName(), PrimitiveTypes.fromReferencedClass(recordComponent.getType())))
                    .toList();
        } else {
            Field[] javaFields = referencedClass.getFields();
            fields = Arrays.stream(javaFields)
                    .map(field -> new FieldDefinition(field.getName(), PrimitiveTypes.fromReferencedClass(field.getType())))
                    .toList();
        }
        return new ObjectType(fields);
    }

    @Override
    public String getDisplayName() {
        String collectedFields = objectFields.stream()
                .map(fieldDefinition -> fieldDefinition.fieldName() + "=" + fieldDefinition.typeDefinition().getDisplayName())
                .collect(Collectors.joining(","));
        return "object({"+collectedFields+"})";
    }

    @Override
    public boolean isCompatible(JsonNode jsonNode) {
        if (jsonNode.isObject()) {
            return objectFields.stream()
                    .allMatch(fieldDefinition -> fieldDefinition.typeDefinition().
                            isCompatible(jsonNode.get(fieldDefinition.fieldName())));
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ObjectType that = (ObjectType) o;
        return Objects.equals(objectFields, that.objectFields);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(objectFields);
    }
}
