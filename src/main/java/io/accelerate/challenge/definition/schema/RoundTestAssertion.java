package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.accelerate.challenge.definition.schema.deserialisers.MapToRoundTestAssertion;
import io.accelerate.challenge.definition.schema.serializers.RoundTestAssertionToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(using = RoundTestAssertionToString.class)
@JsonDeserialize(using = MapToRoundTestAssertion.class)
public record RoundTestAssertion(RoundTestAssertionType type,
                                 Object value) {

}
