package com.giovanna.reviewsmake.infra.exception.user;

public class UsernameAlreadyTakenException extends RuntimeException {
    private static final String defaultMessage = "Username already taken!";

    public UsernameAlreadyTakenException() {
        super(defaultMessage);
    }

    public UsernameAlreadyTakenException(String message) {
        super(message);
    }
}

