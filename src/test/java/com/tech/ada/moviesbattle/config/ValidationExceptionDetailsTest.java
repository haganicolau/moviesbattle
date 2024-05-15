package com.tech.ada.moviesbattle.config;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidationExceptionDetailsTest {

    @Test
    public void testConstructor() {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Validation failed";
        String field = "fieldName";
        String errorMessage = "Field validation error";

        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("objectName", field, errorMessage);
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));

        ValidationExceptionDetails exceptionDetails = new ValidationExceptionDetails(status, message, bindingResult);

        assertEquals(status, exceptionDetails.getStatus());
        assertEquals(message, exceptionDetails.getMessage());
        assertEquals(1, exceptionDetails.getErrors().size());

        ValidationExceptionDetails.FieldValidationError fieldValidationError = exceptionDetails.getErrors().get(0);
        assertEquals(field, fieldValidationError.getField());
        assertEquals(errorMessage, fieldValidationError.getMessage());
    }

}
