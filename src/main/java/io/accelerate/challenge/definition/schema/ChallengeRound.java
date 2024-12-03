package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.accelerate.challenge.definition.schema.serializers.StripTrailingSpaces;

import java.util.List;

/**
* Created by julianghionoiu on 10/01/2015.
*/
@JsonPropertyOrder({"id", "description", "methods", "tests"})
public interface ChallengeRound {

    @JsonProperty("id")
    String getId();

    @JsonProperty("description")
    @JsonSerialize(using = StripTrailingSpaces.class)
    String getDescription();

    @JsonProperty("methods")
    MethodDefinitions getMethods();

    @JsonProperty("tests")
    List<RoundTest> getTests();

}
