package io.accelerate.challenge.definition.schema;

import io.accelerate.challenge.definition.schema.types.PrimitiveType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class MethodDefinitionTest {

    @Test
    void render_method_display_description() {
        ParamDefinition param1 = new ParamDefinition("some param 1", PrimitiveType.INTEGER);
        ParamDefinition param2 = new ParamDefinition("some param 2", PrimitiveType.STRING);
        ReturnDefinition returnType = new ReturnDefinition("some return type", PrimitiveType.STRING);

        MethodDefinition methodDefinition = new MethodDefinition("methodName", List.of(param1, param2), returnType);

        String displayDescription = methodDefinition.getDisplayDescription();
        
        assertThat(displayDescription, is("""
                methodName(integer, string) -> string
                 - param[0] = some param 1
                 - param[1] = some param 2
                 - @return = some return type\
                """));
    }
}