package io.accelerate.challenge.definition.schema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.stream.Collectors;

@JsonPropertyOrder({"name", "params", "returns"})
public record MethodDefinition(@JsonProperty("name") String name,
                               @JsonProperty("params") List<ParamDefinition> parameterDefinitions,
                               @JsonProperty("returns") ReturnDefinition returnDefinition) {

    @JsonIgnore
    public String getDisplayDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(renderMethodDefinition()).append("\n");
        for (int i = 0; i < this.parameterDefinitions.size(); i++) {
            ParamDefinition paramDefinition = this.parameterDefinitions.get(i);
            sb.append(" - param[").append(i).append("] = ").append(paramDefinition.description()).append("\n");
        }
        sb.append(" - @return = ").append(this.returnDefinition.description());
        return sb.toString();
    }

    private String renderMethodDefinition() {
        String parameters = this.parameterDefinitions.stream()
                .map(ParamDefinition::typeDefinition)
                .map(TypeDefinition::getDisplayName)
                .collect(Collectors.joining(", "));
        return this.name + "(" + parameters + ") -> " + this.returnDefinition.typeDefinition().getDisplayName();
    }
}
