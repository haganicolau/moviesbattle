package com.tech.ada.moviesbattle.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NotExistMoviesExceptionTest {

    @Test
    void testExceptionMessage() {
        String errorMessage = "Movies do not exist";

        NotExistMoviesException exception = assertThrows(NotExistMoviesException.class, () -> {
            throw new NotExistMoviesException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionWithCause() {
        String errorMessage = "Movies do not exist";
        Exception cause = new Exception("Root cause message");

        NotExistMoviesException exception = assertThrows(NotExistMoviesException.class, () -> {
            throw new NotExistMoviesException(errorMessage, cause);
        });

        assertEquals(errorMessage, exception.getMessage());

        assertEquals(cause, exception.getCause());
    }
}