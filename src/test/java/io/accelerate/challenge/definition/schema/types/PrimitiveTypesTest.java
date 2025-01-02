package io.accelerate.challenge.definition.schema.types;

import org.junit.jupiter.api.Test;

import java.util.List;

import static io.accelerate.challenge.definition.utils.JsonUtils.asJsonNode;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class PrimitiveTypesTest {

    // ~~~~~  String  ~~~~~

    @Test
    void stringBuildFromName() {
        assertThat(PrimitiveTypes.fromDisplayName("string"), equalTo(PrimitiveTypes.STRING));
    }
    
    @Test
    void stringBuildFromClass() {
        assertThat(PrimitiveTypes.fromReferencedClass(String.class), equalTo(PrimitiveTypes.STRING));
    }

    @Test
    void stringCompatibleWithString() {
        assertThat(PrimitiveTypes.STRING.isCompatible(asJsonNode("someText")), equalTo(true));
    }

    @Test
    void stringNotCompatibleWithInteger() {
        assertThat(PrimitiveTypes.STRING.isCompatible(asJsonNode(2)), equalTo(false));
    }

    @Test
    void stringNotCompatibleWithArray() {
        assertThat(PrimitiveTypes.STRING.isCompatible(asJsonNode(List.of("x", "y"))), equalTo(false));
    }

    @Test
    void stringNotCompatibleWithNull() {
        assertThat(PrimitiveTypes.STRING.isCompatible(asJsonNode(null)), equalTo(false));
    }

    // ~~~~~  Integer  ~~~~~

    @Test
    void integerBuildFromName() {
        assertThat(PrimitiveTypes.fromDisplayName("integer"), equalTo(PrimitiveTypes.INTEGER));
    }
    
    @Test
    void integerBuildFromUnboxedClass() {
        assertThat(PrimitiveTypes.fromReferencedClass(int.class), equalTo(PrimitiveTypes.INTEGER));
    }

    @Test
    void integerBuildFromBoxedClass() {
        assertThat(PrimitiveTypes.fromReferencedClass(Integer.class), equalTo(PrimitiveTypes.INTEGER));
    }

    @Test
    void integerCompatibleWithInteger() {
        assertThat(PrimitiveTypes.INTEGER.isCompatible(asJsonNode(5)), equalTo(true));
    }

    @Test
    void integerNotCompatibleWithFloatingPointNumber() {
        assertThat(PrimitiveTypes.INTEGER.isCompatible(asJsonNode(1.30)), equalTo(false));
    }
    
    @Test
    void integerNotCompatibleWithString() {
        assertThat(PrimitiveTypes.INTEGER.isCompatible(asJsonNode("2")), equalTo(false));
    }

    @Test
    void integerNotCompatibleWithArray() {
        assertThat(PrimitiveTypes.INTEGER.isCompatible(asJsonNode(List.of(1, 2))), equalTo(false));
    }


    @Test
    void integerNotCompatibleWithNull() {
        assertThat(PrimitiveTypes.INTEGER.isCompatible(asJsonNode(null)), equalTo(false));
    }

    // ~~~~~  Boolean  ~~~~~
    
    @Test
    void booleanBuildFromName() {
        assertThat(PrimitiveTypes.fromDisplayName("boolean"), equalTo(PrimitiveTypes.BOOLEAN));
    }
    
    @Test
    void booleanBuildFromUnboxedClass() {
        assertThat(PrimitiveTypes.fromReferencedClass(boolean.class), equalTo(PrimitiveTypes.BOOLEAN));
    }

    @Test
    void booleanBuildFromBoxedClass() {
        assertThat(PrimitiveTypes.fromReferencedClass(Boolean.class), equalTo(PrimitiveTypes.BOOLEAN));
    }

    @Test
    void booleanCompatibleWithBoolean() {
        assertThat(PrimitiveTypes.BOOLEAN.isCompatible(asJsonNode(true)), equalTo(true));
    }

    @Test
    void booleanNotCompatibleWithInteger() {
        assertThat(PrimitiveTypes.BOOLEAN.isCompatible(asJsonNode(0)), equalTo(false));
    }

    @Test
    void booleanNotCompatibleWithArray() {
        assertThat(PrimitiveTypes.BOOLEAN.isCompatible(asJsonNode(List.of(true, false))), equalTo(false));
    }

    @Test
    void booleanNotCompatibleWithNull() {
        assertThat(PrimitiveTypes.BOOLEAN.isCompatible(asJsonNode(null)), equalTo(false));
    }
}