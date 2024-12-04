package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.accelerate.challenge.definition.schema.deserialisers.StringToClass;
import io.accelerate.challenge.definition.schema.serializers.ClassToString;

import java.util.List;

@JsonPropertyOrder({"name", "params", "returns"})
public record MethodDefinition(@JsonProperty("name") String name,
                               @JsonSerialize(contentUsing = ClassToString.class)
                               @JsonDeserialize(contentUsing = StringToClass.class)
                               @JsonProperty("params") List<Class<?>> parameterTypes,
                               @JsonSerialize(using = ClassToString.class)
                               @JsonDeserialize(using = StringToClass.class)
                               @JsonProperty("returns") Class<?> returnType) {
}
