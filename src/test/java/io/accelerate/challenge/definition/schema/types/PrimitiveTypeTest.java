package io.accelerate.challenge.definition.schema.types;

import org.junit.jupiter.api.Test;

import java.util.List;

import static io.accelerate.challenge.definition.utils.JsonUtils.asJsonNode;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class PrimitiveTypeTest {

    // ~~~~~  String  ~~~~~

    @Test
    void stringBuildFromName() {
        assertThat(PrimitiveType.fromDisplayName("string"), equalTo(PrimitiveType.STRING));
    }
    
    @Test
    void stringBuildFromClass() {
        assertThat(PrimitiveType.fromReferencedClass(String.class), equalTo(PrimitiveType.STRING));
    }

    @Test
    void stringCompatibleWithString() {
        assertThat(PrimitiveType.STRING.isCompatible(asJsonNode("someText")), equalTo(true));
    }

    @Test
    void stringNotCompatibleWithInteger() {
        assertThat(PrimitiveType.STRING.isCompatible(asJsonNode(2)), equalTo(false));
    }

    @Test
    void stringNotCompatibleWithArray() {
        assertThat(PrimitiveType.STRING.isCompatible(asJsonNode(List.of("x", "y"))), equalTo(false));
    }

    @Test
    void stringNotCompatibleWithNull() {
        assertThat(PrimitiveType.STRING.isCompatible(asJsonNode(null)), equalTo(false));
    }

    // ~~~~~  Integer  ~~~~~

    @Test
    void integerBuildFromName() {
        assertThat(PrimitiveType.fromDisplayName("integer"), equalTo(PrimitiveType.INTEGER));
    }
    
    @Test
    void integerBuildFromUnboxedClass() {
        assertThat(PrimitiveType.fromReferencedClass(int.class), equalTo(PrimitiveType.INTEGER));
    }

    @Test
    void integerBuildFromBoxedClass() {
        assertThat(PrimitiveType.fromReferencedClass(Integer.class), equalTo(PrimitiveType.INTEGER));
    }

    @Test
    void integerCompatibleWithInteger() {
        assertThat(PrimitiveType.INTEGER.isCompatible(asJsonNode(5)), equalTo(true));
    }

    @Test
    void integerNotCompatibleWithFloatingPointNumber() {
        assertThat(PrimitiveType.INTEGER.isCompatible(asJsonNode(1.30)), equalTo(false));
    }
    
    @Test
    void integerNotCompatibleWithString() {
        assertThat(PrimitiveType.INTEGER.isCompatible(asJsonNode("2")), equalTo(false));
    }

    @Test
    void integerNotCompatibleWithArray() {
        assertThat(PrimitiveType.INTEGER.isCompatible(asJsonNode(List.of(1, 2))), equalTo(false));
    }


    @Test
    void integerNotCompatibleWithNull() {
        assertThat(PrimitiveType.INTEGER.isCompatible(asJsonNode(null)), equalTo(false));
    }

    // ~~~~~  Boolean  ~~~~~
    
    @Test
    void booleanBuildFromName() {
        assertThat(PrimitiveType.fromDisplayName("boolean"), equalTo(PrimitiveType.BOOLEAN));
    }
    
    @Test
    void booleanBuildFromUnboxedClass() {
        assertThat(PrimitiveType.fromReferencedClass(boolean.class), equalTo(PrimitiveType.BOOLEAN));
    }

    @Test
    void booleanBuildFromBoxedClass() {
        assertThat(PrimitiveType.fromReferencedClass(Boolean.class), equalTo(PrimitiveType.BOOLEAN));
    }

    @Test
    void booleanCompatibleWithBoolean() {
        assertThat(PrimitiveType.BOOLEAN.isCompatible(asJsonNode(true)), equalTo(true));
    }

    @Test
    void booleanNotCompatibleWithInteger() {
        assertThat(PrimitiveType.BOOLEAN.isCompatible(asJsonNode(0)), equalTo(false));
    }

    @Test
    void booleanNotCompatibleWithArray() {
        assertThat(PrimitiveType.BOOLEAN.isCompatible(asJsonNode(List.of(true, false))), equalTo(false));
    }

    @Test
    void booleanNotCompatibleWithNull() {
        assertThat(PrimitiveType.BOOLEAN.isCompatible(asJsonNode(null)), equalTo(false));
    }
}