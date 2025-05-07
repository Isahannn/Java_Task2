package org.example.exception;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class StraightLineEntityExceptionTests {

    @Test
    public void testExceptionWithErrorTypeOnly() {

        StraightLineEntityException.ErrorType errorType = StraightLineEntityException.ErrorType.DIVISION_BY_ZERO;

        StraightLineEntityException exception = new StraightLineEntityException(errorType);

        assertNotNull(exception);
        assertEquals(exception.getErrorType(), errorType);
        assertEquals(exception.getMessage(), errorType.getDefaultMessage());
    }

    @Test
    public void testExceptionWithCustomMessage() {
        StraightLineEntityException.ErrorType errorType = StraightLineEntityException.ErrorType.FILE_OPERATION;
        String customMessage = "Custom file operation error occurred";

        StraightLineEntityException exception = new StraightLineEntityException(errorType, customMessage);

        assertNotNull(exception);
        assertEquals(exception.getErrorType(), errorType);
        assertEquals(exception.getMessage(), customMessage);
    }

    @Test
    public void testExceptionWithCause() {
        StraightLineEntityException.ErrorType errorType = StraightLineEntityException.ErrorType.PARSING_ERROR;
        String customMessage = "Parsing error with cause";
        Throwable cause = new IllegalArgumentException("Invalid input format");


        StraightLineEntityException exception = new StraightLineEntityException(errorType, customMessage, cause);
        assertNotNull(exception);
        assertEquals(exception.getErrorType(), errorType);
        assertEquals(exception.getMessage(), customMessage);
        assertEquals(exception.getCause(), cause);
    }

    @Test
    public void testErrorTypeDefaultMessage() {
        for (StraightLineEntityException.ErrorType errorType : StraightLineEntityException.ErrorType.values()) {
            assertNotNull(errorType.getDefaultMessage(), "Default message should not be null");
        }
    }
}