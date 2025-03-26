package com.spotride.spotride.exception;

/**
 * Exception when user email is not unique.
 */
public class EmailAlreadyTakenException extends RuntimeException {
    public EmailAlreadyTakenException(String email) {
        super("User with email " + email + " already exists");
    }
}
