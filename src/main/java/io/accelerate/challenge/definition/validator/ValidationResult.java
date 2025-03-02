package io.accelerate.challenge.definition.validator;

import com.github.erosb.jsonsKema.ValidationFailure;

public record ValidationResult(ValidationFailure maybeValidationFailure) {

    public boolean hasFailures() {
        return maybeValidationFailure != null;
    }

    public void printToStdErr() {
        if (maybeValidationFailure != null) {
            System.err.println(validationErrorsToString(maybeValidationFailure, 0));
        }
    }

    @Override
    public String toString() {
        if (maybeValidationFailure != null) {
            return "ValidationResult - failed\n" + validationErrorsToString(maybeValidationFailure, 0) ;
        } else {
            return "ValidationResult - passed\n";
        }
    }

    private static String validationErrorsToString(ValidationFailure e, int level) {
        // Indentation for better readability
        String indent = "  ".repeat(level);

        StringBuilder errorMessage = new StringBuilder();
        // Print the current error
        errorMessage.append(indent).append("- ").append(e.getMessage()).append("\n");

        // Recursively print sub-errors (caused by nested schemas or array items)
        for (ValidationFailure subError : e.getCauses()) {
            errorMessage.append(validationErrorsToString(subError, level + 1));
        }
        
        return errorMessage.toString();
    }
}
