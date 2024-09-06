package com.giovanna.reviewsmake.infra.exception.user;

public class NoUsersFoundException extends RuntimeException {
    private static final String defaultMessage = "No users found!";

    public NoUsersFoundException() {
        super(defaultMessage);
    }

    public NoUsersFoundException(String message) {
        super(message);
    }
}
