package io.accelerate.challenge.definition.validate;

import com.github.erosb.jsonsKema.ValidationFailure;

public record ValidationResult(ValidationFailure maybeValidationFailure) {

    public boolean hasFailures() {
        return maybeValidationFailure != null;
    }

    public void printToStdErr() {
        if (maybeValidationFailure != null) {
            printValidationErrors(maybeValidationFailure, 0);
        }
    }

    private static void printValidationErrors(ValidationFailure e, int level) {
        // Indentation for better readability
        String indent = "  ".repeat(level);

        // Print the current error
        System.err.println(indent + "- " + e.getMessage());

        // Recursively print sub-errors (caused by nested schemas or array items)
        for (ValidationFailure subError : e.getCauses()) {
            printValidationErrors(subError, level + 1);
        }
    }
}
