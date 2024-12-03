package io.accelerate.challenge.definition.schema.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ClassToString extends JsonSerializer<Class<?>> {

    @Override
    public void serialize(Class<?> c, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String[] classComponents = c.getCanonicalName().split("\\.");
        String simpleName = classComponents[classComponents.length - 1];
        jsonGenerator.writeString(simpleName.toLowerCase());
    }
}