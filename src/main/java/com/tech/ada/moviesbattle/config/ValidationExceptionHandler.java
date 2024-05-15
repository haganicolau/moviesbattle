package com.tech.ada.moviesbattle.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * That class, annotated with @RestControllerAdvice, is a Spring component that acts as a
 * global controller for handling validation exceptions throughout your application. When validation errors occur,
 * such as form field validation, this class can capture these exceptions and provide an appropriate response.
 */
@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationExceptionDetails handleValidationExceptions(MethodArgumentNotValidException ex) {
        return new ValidationExceptionDetails(HttpStatus.BAD_REQUEST,
                "Validation error: ", ex.getBindingResult());
    }

}
