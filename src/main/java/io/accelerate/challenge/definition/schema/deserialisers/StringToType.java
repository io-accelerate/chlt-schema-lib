package io.accelerate.challenge.definition.schema.deserialisers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.accelerate.challenge.definition.schema.TypeDefinition;
import io.accelerate.challenge.definition.schema.types.FieldDefinition;
import io.accelerate.challenge.definition.schema.types.ListType;
import io.accelerate.challenge.definition.schema.types.ObjectType;
import io.accelerate.challenge.definition.schema.types.PrimitiveType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringToType extends JsonDeserializer<TypeDefinition> {
    @Override
    public TypeDefinition deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String value = jsonParser.getValueAsString();

        if (value.startsWith("list")) {
            // Extract the type from the string "list(<type>)"
            String type = value.substring(value.indexOf("(") + 1, value.indexOf(")"));
            return new ListType(PrimitiveType.fromDisplayName(type));
        } else
        if (value.startsWith("object")) {
            // Extract the fields from the string "object({someField=integer,otherField=string})"
            String fieldsString = value.substring(value.indexOf("{") + 1, value.indexOf("}")); // Removes "object({" and "})"
            String[] fields = fieldsString.split(",");

            List<FieldDefinition> fieldDefinitions = new ArrayList<>();
            for (String field : fields) {
                String[] parts = field.split("=");
                String fieldName = parts[0].trim();
                String fieldType = parts[1].trim();
                fieldDefinitions.add(new FieldDefinition(fieldName, PrimitiveType.fromDisplayName(fieldType)));
            }
            return new ObjectType(fieldDefinitions);
        } else {
            return PrimitiveType.fromDisplayName(value);
        }
    }
}
