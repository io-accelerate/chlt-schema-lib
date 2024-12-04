package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.accelerate.challenge.definition.Constants;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"$schema", "id", "version", "name", "rounds"})
public class Challenge {
    private final String id;
    private final Integer version;
    private final String name;
    private final List<ChallengeRound> rounds;

    @JsonCreator
    public Challenge(
            @JsonProperty("id") String id,
            @JsonProperty("version") Integer version,
            @JsonProperty("name") String name,
            @JsonProperty("rounds") List<ChallengeRound> rounds) {
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
    public List<ChallengeRound> getRounds() {
        return rounds;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Challenge.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("version=" + version)
                .add("name='" + name + "'")
                .add("rounds=" + rounds)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Challenge challenge = (Challenge) o;
        return Objects.equals(id, challenge.id) &&
                Objects.equals(version, challenge.version) &&
                Objects.equals(name, challenge.name) &&
                Objects.equals(rounds, challenge.rounds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, name, rounds);
    }
}
