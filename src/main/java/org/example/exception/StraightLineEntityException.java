package org.example.exception;

public class StraightLineEntityException extends Exception {


    private final ErrorType errorType;

    public StraightLineEntityException(ErrorType errorType) {
        super(errorType.getDefaultMessage());
        this.errorType = errorType;
    }

    public StraightLineEntityException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public StraightLineEntityException(ErrorType errorType, String message, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
    public enum ErrorType {
        NULL_LINE("annot divide by zero"),
        EMPTY_INPUT("annot divide by zero"),
        DIVISION_BY_ZERO("Cannot divide by zero"),
        FILE_OPERATION("File operation failed"),
        INVALID_LINE_DATA("Invalid line data"),
        PARSING_ERROR("Error while parsing straight line input data"),
        CALCULATION_ERROR("Error in straight line calculation"),
        VALIDATION_ERROR("Validation failed for straight line entity"),
        NO_INTERSECTION("The line does not intersect any axis"),
        PARALLEL_LINES("The line does not intersect any axis");

        private final String defaultMessage;

        ErrorType(String defaultMessage) {
            this.defaultMessage = defaultMessage;
        }

        public String getDefaultMessage() {
            return defaultMessage;
        }
    }

}
