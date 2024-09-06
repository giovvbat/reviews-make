package com.giovanna.reviewsmake.infra.exception.jwt;

public class ExpiredTokenException extends RuntimeException {
    private static final String defaultMessage = "Expired token provided!";

    public ExpiredTokenException() {
        super(defaultMessage);
    }

    public ExpiredTokenException(String message) {
        super(message);
    }
}
