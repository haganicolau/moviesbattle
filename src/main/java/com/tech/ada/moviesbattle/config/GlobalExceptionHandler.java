package com.tech.ada.moviesbattle.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * That class, annotated with @RestControllerAdvice, is a Spring component that acts as a
 * global controller for handling exceptions throughout your application. When validation errors occur,
 * such as form field validation, this class can capture these exceptions and provide an appropriate response.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final ObjectMapper objectMapper;

    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception ex) {

        LOG.error("Internal Server Error: {}", ex.getMessage(), ex);
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());

        String jsonError;
        try {
            jsonError = objectMapper.writeValueAsString(errorDetails);
        } catch (JsonProcessingException jsonEx) {
            // In case of serialization error, log the error and return a response with status code 500 without body
            LOG.error("Error serializing error message. ", jsonEx);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON).build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonError);
    }

    record ErrorDetails(String message) {
        ErrorDetails(String message) {
            this.message = "Internal Server Error: " + message;
        }
    }
}
