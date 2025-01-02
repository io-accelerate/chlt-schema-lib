package io.accelerate.challenge.definition.schema.deserialisers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.accelerate.challenge.definition.schema.RoundTestAssertion;
import io.accelerate.challenge.definition.schema.RoundTestAssertionType;

import java.io.IOException;
import java.util.Map;

public class MapToRoundTestAssertion extends JsonDeserializer<RoundTestAssertion> {
    @Override
    public RoundTestAssertion deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Map<?,?> map = deserializationContext.readValue(jsonParser, Map.class);
        String firstKey = (String) map.keySet().toArray()[0];
        RoundTestAssertionType roundTestAssertionType =
                RoundTestAssertionType.fromDisplayName(firstKey);

        return new RoundTestAssertion(roundTestAssertionType, map.get(firstKey));
    }
}
