package com.giovanna.reviewsmake.infra.exception.user;

public class EmailAlreadyTakenException extends RuntimeException {
    private static final String defaultMessage = "Email already taken!";

    public EmailAlreadyTakenException() {
        super(defaultMessage);
    }

    public EmailAlreadyTakenException(String message) {
        super(message);
    }
}
