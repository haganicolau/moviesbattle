package com.tech.ada.moviesbattle.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    public GlobalExceptionHandlerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testErrorDetailsConstructor() {

        String errorMessage = "Test Error Message";

        GlobalExceptionHandler.ErrorDetails errorDetails = new GlobalExceptionHandler.ErrorDetails(errorMessage);

        assertEquals("Internal Server Error: " + errorMessage, errorDetails.message());
    }
}
