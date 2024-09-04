package com.giovanna.reviewsmake.exception;

public class UnauthorizedCredentialsException extends RuntimeException {
    private static final String defaultMessage = "Unauthorized credentials!";

    public UnauthorizedCredentialsException() {
        super(defaultMessage);
    }

    public UnauthorizedCredentialsException(String message) {
        super(message);
    }
}
