package io.accelerate.challenge.definition.schema.deserialisers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class StringToClass extends JsonDeserializer<Class<?>> {
    @Override
    public Class<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String value = jsonParser.getValueAsString();

        // Map the lowercase string back to the corresponding class
        return switch (value.toLowerCase()) {
            case "string" -> String.class;
            case "integer" -> Integer.class;
            case "boolean" -> Boolean.class;
            case "double" -> Double.class;
            case "float" -> Float.class;
            case "long" -> Long.class;
            default -> throw new IOException("Unsupported type: " + value);
        };
    }
}
