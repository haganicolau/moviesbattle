//package com.tech.ada.moviesbattle.config;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class GlobalExceptionHandlerTest {
//
//    @Mock
//    private ObjectMapper objectMapper;
//
//    @InjectMocks
//    private GlobalExceptionHandler globalExceptionHandler;
//
//    public GlobalExceptionHandlerTest() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testHandleException() throws JsonProcessingException {
//
//        Exception ex = new Exception("Test Exception");
//        when(objectMapper.writeValueAsString(any())).thenThrow(JsonProcessingException.class);
//        ResponseEntity<String> responseEntity = globalExceptionHandler.handleException(ex);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
//        verify(objectMapper, times(1)).writeValueAsString(any());
//    }
//
//    @Test
//    public void testErrorDetailsConstructor() {
//
//        String errorMessage = "Test Error Message";
//
//        GlobalExceptionHandler.ErrorDetails errorDetails = new GlobalExceptionHandler.ErrorDetails(errorMessage);
//
//        assertEquals("Internal Server Error: " + errorMessage, errorDetails.message());
//    }
//}
