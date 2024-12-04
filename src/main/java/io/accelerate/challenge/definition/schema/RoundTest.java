package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by julianghionoiu on 29/07/2015.
 */
@JsonPropertyOrder({"id", "call", "expect"})
public record RoundTest(@JsonProperty("id") String id,
                        @JsonProperty("call") MethodCall methodCall,
                        @JsonProperty("expect") RoundTestAssertion roundTestAssertion) {

}
