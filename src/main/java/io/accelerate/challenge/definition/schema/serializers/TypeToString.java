package io.accelerate.challenge.definition.schema.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.accelerate.challenge.definition.schema.TypeDefinition;

import java.io.IOException;

public class TypeToString extends JsonSerializer<TypeDefinition> {

    @Override
    public void serialize(TypeDefinition c, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(c.getDisplayName());
    }
}