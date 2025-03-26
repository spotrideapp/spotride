package com.spotride.spotride.exception;

/**
 * Exception when username is not unique.
 */
public class UsernameAlreadyTakenException extends RuntimeException {
    public UsernameAlreadyTakenException(String username) {
        super("User with username " + username + " already exists");
    }
}
