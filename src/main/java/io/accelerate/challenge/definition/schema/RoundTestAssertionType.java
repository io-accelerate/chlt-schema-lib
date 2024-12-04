package io.accelerate.challenge.definition.schema;

public enum RoundTestAssertionType {
    EQUALS,
    CONTAINS,
    ;

    public static RoundTestAssertionType fromPrintableName(String name) {
        for (RoundTestAssertionType type : RoundTestAssertionType.values()) {
            if (type.toPrintableName().equals(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant " + RoundTestAssertionType.class.getName() + "." + name);
    }

    public String toPrintableName() {
        return this.name().toLowerCase();
    }
}
