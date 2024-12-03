package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"method", "args"})
public record MethodCall(@JsonProperty("method") String methodName,
                         @JsonProperty("args") List<?> args) {
}
