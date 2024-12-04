package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.accelerate.challenge.definition.Constants;

import java.util.List;

@JsonPropertyOrder({"$schema", "id", "version", "name", "rounds"})
public class ChallengeBase {
    private final String id;
    private final Integer version;
    private final String name;
    private final List<ChallengeRoundBase> rounds;

    @JsonCreator
    public ChallengeBase(
            @JsonProperty("id") String id,
            @JsonProperty("version") Integer version,
            @JsonProperty("name") String name,
            @JsonProperty("rounds") List<ChallengeRoundBase> rounds) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.rounds = rounds;
    }

    @JsonProperty("$schema")
    public String getSchema() {
        return Constants.SCHEMA_URL;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("version")
    public int getVersion() {
        return version;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("rounds")
    public List<ChallengeRoundBase> getRounds() {
        return rounds;
    }
}
