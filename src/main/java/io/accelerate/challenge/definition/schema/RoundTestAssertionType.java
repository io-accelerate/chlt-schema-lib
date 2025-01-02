package io.accelerate.challenge.definition.schema;

public enum RoundTestAssertionType {
    EQUALS("equals"),
    IS_NULL("isNull"),
    CONTAINS_STRING("containsString"),
    CONTAINS_STRING_IGNORING_CASE("containsStringIgnoringCase"),
    ;

    private final String displayName;

    RoundTestAssertionType(String displayName) {
        this.displayName = displayName;
    }

    public static RoundTestAssertionType fromDisplayName(String name) {
        for (RoundTestAssertionType type : RoundTestAssertionType.values()) {
            if (type.toDisplayName().equals(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant " + RoundTestAssertionType.class.getName() + "." + name);
    }

    public String toDisplayName() {
        return this.displayName;
    }
}
