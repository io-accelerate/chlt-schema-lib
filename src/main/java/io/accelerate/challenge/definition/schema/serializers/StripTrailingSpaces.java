package io.accelerate.challenge.definition.schema.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class StripTrailingSpaces extends JsonSerializer<String> {

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String[] lines = s.split("\n");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < lines.length; i++) {
            result.append(lines[i].stripTrailing());
            if (i < lines.length - 1) {
                result.append("\n");
            }
        }
        jsonGenerator.writeString(result.toString());
    }
}
