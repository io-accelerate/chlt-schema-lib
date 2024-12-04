package io.accelerate.challenge.definition.schema.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.accelerate.challenge.definition.schema.RoundTestAssertion;

import java.io.IOException;
import java.util.Map;

public class RoundTestAssertionToString extends JsonSerializer<RoundTestAssertion> {

    @Override
    public void serialize(RoundTestAssertion roundTestAssertion, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(Map.of(roundTestAssertion.type().toPrintableName(), roundTestAssertion.value()));
    }
}
