package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.accelerate.challenge.definition.schema.deserialisers.StringToType;
import io.accelerate.challenge.definition.schema.serializers.TypeToString;

public record ReturnDefinition(@JsonProperty("description") String description,
                               @JsonSerialize(using = TypeToString.class)
                               @JsonDeserialize(using = StringToType.class)
                               @JsonProperty("type") TypeDefinition typeDefinition) {
}
