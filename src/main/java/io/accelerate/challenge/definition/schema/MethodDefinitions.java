package io.accelerate.challenge.definition.schema;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

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
}
