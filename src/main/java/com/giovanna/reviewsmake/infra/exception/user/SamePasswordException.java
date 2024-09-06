package com.giovanna.reviewsmake.infra.exception.user;

public class SamePasswordException extends RuntimeException {
    private static final String defaultMessage = "New password must be different from current password!";

    public SamePasswordException() {
        super(defaultMessage);
    }

    public SamePasswordException(String message) {
        super(message);
    }
}
