package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"name", "params", "returns"})
public record MethodDefinition(@JsonProperty("name") String name,
                               @JsonProperty("params") List<ParamDefinition> parameterDefinitions,
                               @JsonProperty("returns") ParamDefinition returnParamDefinition) {
}
