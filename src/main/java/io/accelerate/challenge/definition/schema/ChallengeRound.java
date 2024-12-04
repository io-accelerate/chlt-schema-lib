package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.accelerate.challenge.definition.schema.serializers.StripTrailingSpaces;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@JsonPropertyOrder({"id", "description", "methods", "tests"})
@SuppressWarnings("ClassCanBeRecord")
public class ChallengeRound {
    private final String id;
    private final String description;
    private final MethodDefinitions methods;
    private final List<RoundTest> tests;

    @JsonCreator
    public ChallengeRound(@JsonProperty("id") String id,
                          @JsonProperty("description") String description,
                          @JsonProperty("methods") MethodDefinitions methods,
                          @JsonProperty("tests") List<RoundTest> tests) {
        this.id = id;
        this.description = description;
        this.methods = methods;
        this.tests = tests;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("description")
    @JsonSerialize(using = StripTrailingSpaces.class)
    public String getDescription() {
        return description;
    }

    @JsonProperty("methods")
    public MethodDefinitions getMethods() {
        return methods;
    }

    @JsonProperty("tests")
    public List<RoundTest> getTests() {
        return tests;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ChallengeRound.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("description='" + description + "'")
                .add("methods=" + methods)
                .add("tests=" + tests)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChallengeRound that = (ChallengeRound) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(description, that.description) &&
                Objects.equals(methods, that.methods) &&
                Objects.equals(tests, that.tests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, methods, tests);
    }
}
