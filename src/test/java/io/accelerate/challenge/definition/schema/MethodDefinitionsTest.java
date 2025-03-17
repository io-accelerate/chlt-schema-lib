package io.accelerate.challenge.definition.schema;

import io.accelerate.challenge.definition.schema.types.PrimitiveType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class MethodDefinitionsTest {
    
    @Test
    void get_method_by_name() {
        ReturnDefinition returnType = new ReturnDefinition("some return type", PrimitiveType.STRING);
        MethodDefinition methodDefinition1 = new MethodDefinition("method1", List.of(), returnType);
        MethodDefinition methodDefinition2 = new MethodDefinition("method2", List.of(), returnType);

        MethodDefinitions methodDefinitions = MethodDefinitions.of(List.of(methodDefinition1, methodDefinition2));

        MethodDefinition methodDefinition = methodDefinitions.getByName("method1");
        assertThat(methodDefinition, is(methodDefinition1));
    }

    @Test
    void render_methods_display_descriptions() {
        ParamDefinition param1 = new ParamDefinition("some param 1", PrimitiveType.INTEGER);
        ReturnDefinition returnType1 = new ReturnDefinition("some return type", PrimitiveType.STRING);
        MethodDefinition methodDefinition1 = new MethodDefinition("methodName", List.of(param1), returnType1);
        
        ParamDefinition param2 = new ParamDefinition("some param 2", PrimitiveType.INTEGER);
        ReturnDefinition returnType2 = new ReturnDefinition("some return type", PrimitiveType.STRING);
        MethodDefinition methodDefinition2 = new MethodDefinition("methodName", List.of(param2), returnType2);

        MethodDefinitions methodDefinitions = MethodDefinitions.of(List.of(methodDefinition1, methodDefinition2));

        String displayDescription = methodDefinitions.getDisplayDescription();

        assertThat(displayDescription, is("""
                In order to complete the round you need to implement the following methods:
                
                methodName(integer) -> string
                 - param[0] = some param 1
                 - @return = some return type
                
                methodName(integer) -> string
                 - param[0] = some param 2
                 - @return = some return type\
                """));
        
    }
    
    
}