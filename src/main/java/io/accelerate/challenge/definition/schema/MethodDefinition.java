package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.accelerate.challenge.definition.schema.serializers.ClassToString;

import java.util.List;

@JsonPropertyOrder({"name", "params", "returns"})
public record MethodDefinition(@JsonProperty("name") String name,
                               @JsonProperty("params") @JsonSerialize(contentUsing = ClassToString.class) List<Class<?>> parameterTypes,
                               @JsonProperty("returns") @JsonSerialize(using = ClassToString.class) Class<?> returnType) {
}
