package io.accelerate.challenge.definition.schema.deserialisers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.accelerate.challenge.definition.schema.RoundTestAssertion;

import java.io.IOException;

public class MapToTestAssertion extends JsonDeserializer<RoundTestAssertion>  {

    @Override
    public RoundTestAssertion deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return null;
    }
}
