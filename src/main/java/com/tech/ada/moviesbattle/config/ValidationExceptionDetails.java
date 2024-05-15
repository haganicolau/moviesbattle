package com.tech.ada.moviesbattle.config;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that handles responses to requests when an exception is thrown. It is not necessary to show too much
 * information about the application's state and the request.
 */
public class ValidationExceptionDetails {
    private HttpStatus status;
    private String message;
    private List<FieldValidationError> errors;

    public ValidationExceptionDetails(HttpStatus status, String message, BindingResult bindingResult) {
        this.status = status;
        this.message = message;
        this.errors = new ArrayList<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            FieldValidationError fieldError = new FieldValidationError(error.getField(), error.getDefaultMessage());
            errors.add(fieldError);
        }
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<FieldValidationError> getErrors() {
        return errors;
    }

    public static class FieldValidationError {
        private String field;
        private String message;

        public FieldValidationError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }
    }
}
