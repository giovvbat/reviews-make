package com.giovanna.reviewsmake.infra.exception.user;

public class UnauthorizedCredentialsException extends RuntimeException {
    private static final String defaultMessage = "Unauthorized credentials!";

    public UnauthorizedCredentialsException() {
        super(defaultMessage);
    }

    public UnauthorizedCredentialsException(String message) {
        super(message);
    }
}
