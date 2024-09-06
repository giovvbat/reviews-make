package com.giovanna.reviewsmake.infra.exception.jwt;

public class InvalidTokenException extends RuntimeException {
    private static final String defaultMessage = "Invalid token provided!";

    public InvalidTokenException() {
        super(defaultMessage);
    }

    public InvalidTokenException(String message) {
        super(message);
    }
}
