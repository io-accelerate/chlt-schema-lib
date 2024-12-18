package io.accelerate.challenge.definition.schema;

public enum RoundTestAssertionType {
    EQUALS("equals"),
    CONTAINS_STRING("containsString"),
    CONTAINS_STRING_IGNORING_CASE("containsStringIgnoringCase"),
    ;

    private final String printableName;

    RoundTestAssertionType(String printableName) {
        this.printableName = printableName;
    }

    public static RoundTestAssertionType fromPrintableName(String name) {
        for (RoundTestAssertionType type : RoundTestAssertionType.values()) {
            if (type.toPrintableName().equals(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant " + RoundTestAssertionType.class.getName() + "." + name);
    }

    public String toPrintableName() {
        return this.printableName;
    }
}
