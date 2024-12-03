package io.accelerate.challenge.definition.schema;

import java.util.ArrayList;

public class MethodDefinitions extends ArrayList<MethodDefinition> {
    public MethodDefinition getByName(String methodName) {
        for (MethodDefinition methodDefinition : this) {
            if (methodDefinition.name().equals(methodName)) {
                return methodDefinition;
            }
        }
        throw new IllegalArgumentException("Method " + methodName + " not found");
    }
}
