package io.accelerate.challenge.definition.schema;

public enum RoundTestAssertionType {
    EQUALS("equals", TypeConstraint.MATCHING_RETURN_TYPE),
    IS_NULL("isNull", TypeConstraint.ANY),
    CONTAINS_STRING("containsString", TypeConstraint.STRING),
    CONTAINS_STRING_IGNORING_CASE("containsStringIgnoringCase", TypeConstraint.STRING);

    private final String displayName;
    private final TypeConstraint typeConstraint;

    RoundTestAssertionType(String displayName, TypeConstraint typeConstraint) {
        this.displayName = displayName;
        this.typeConstraint = typeConstraint;
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
    
    @SuppressWarnings("unused")
    public TypeConstraint getTypeConstraint() {
        return this.typeConstraint;
    }
}
