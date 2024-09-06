package com.giovanna.reviewsmake.infra.exception.jwt;

public class NoTokenException extends RuntimeException {
    private static final String defaultMessage = "No token provided!";

    public NoTokenException() {
        super(defaultMessage);
    }

    public NoTokenException(String message) {
        super(message);
    }
}
