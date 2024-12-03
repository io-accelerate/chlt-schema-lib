package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface RoundTestAssertion {

    @JsonIgnore
    boolean passesAssertion(Object value);

    @JsonIgnore
    Class<?> getExpectedClass();

    @JsonIgnore
    String getExpectationAsString();
}
