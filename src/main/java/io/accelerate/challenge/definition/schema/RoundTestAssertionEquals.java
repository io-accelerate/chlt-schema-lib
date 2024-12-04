package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoundTestAssertionEquals implements RoundTestAssertion {
    @JsonProperty("equals")
    private Object expectedValue;

    public RoundTestAssertionEquals(Object expectedValue) {
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean passesAssertion(Object value) {
        return expectedValue.equals(value);
    }

    @Override
    public Class<?> getExpectedClass() {
        return expectedValue.getClass();
    }

    @Override
    public String getExpectationAsString(){
        return expectedValue.toString();
    }
}
