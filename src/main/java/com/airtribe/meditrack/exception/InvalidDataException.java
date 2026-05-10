package com.airtribe.meditrack.exception;

/**
 * Exception thrown in case of invalid input
 */
public class InvalidDataException extends RuntimeException {


    public InvalidDataException(String message) {
        super(message);
    }
}
