package com.tech.ada.moviesbattle.exception;

/**
 * An exception should be thrown when there is a schedule conflict between the created availabilities.
 */
public class NotExistMoviesException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotExistMoviesException(String message) {
        super(message);
    }

    public NotExistMoviesException(String message, Throwable cause) {
        super(message, cause);
    }
}
