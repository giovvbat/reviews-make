package com.giovanna.reviewsmake.infra.exception.user;

public class UserNotLoggedException extends RuntimeException {
    private static final String defaultMessage = "user not logged";

    public UserNotLoggedException() {
        super(defaultMessage);
    }

    public UserNotLoggedException(String message) {
        super(message);
    }
}
