package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"$schema", "id", "name", "rounds"})
public interface Challenge {

    @JsonProperty("$schema")
    default String getSchema() {
        return "https://get.accelerate.io/challenge-toolkit/schema/0.1.4";
    }

    @JsonProperty("version")
    int getVersion();

    @JsonProperty("id")
    String getId();

    @JsonProperty("name")
    String getName();

    @JsonProperty("rounds")
    List<ChallengeRound> getRounds();
}
