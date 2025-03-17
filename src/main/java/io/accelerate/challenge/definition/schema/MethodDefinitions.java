package io.accelerate.challenge.definition.schema;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class MethodDefinitions extends ArrayList<MethodDefinition> {

    public static MethodDefinitions of(MethodDefinition methodDefinition) {
        MethodDefinitions methodDefinitions = new MethodDefinitions();
        methodDefinitions.add(methodDefinition);
        return methodDefinitions;
    }

    public static MethodDefinitions of(@NotNull Collection<MethodDefinition> methodDefinitions) {
        MethodDefinitions methodDefinitionsNew = new MethodDefinitions();
        methodDefinitionsNew .addAll(methodDefinitions);
        return methodDefinitionsNew ;
    }

    public MethodDefinition getByName(String methodName) {
        for (MethodDefinition methodDefinition : this) {
            if (methodDefinition.name().equals(methodName)) {
                return methodDefinition;
            }
        }
        throw new IllegalArgumentException("Method " + methodName + " not found");
    }

    public String getDisplayDescription() {
        StringBuilder sb = new StringBuilder();
        String maybePluralSuffix = this.size() > 1 ? "s" : "";
        sb.append("In order to complete the round you need to implement the following method").append(maybePluralSuffix).append(":").append("\n\n");
        String methods = this.stream().map(MethodDefinition::getDisplayDescription).collect(Collectors.joining("\n\n"));
        sb.append(methods);
        return sb.toString();
    }
}
