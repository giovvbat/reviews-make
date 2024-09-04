package com.giovanna.reviewsmake.exception;

public class ReviewNotFoundException extends RuntimeException {
    private static final String defaultMessage = "Review not found!";

    public ReviewNotFoundException() {
        super(defaultMessage);
    }

    public ReviewNotFoundException(String message) {
        super(message);
    }
}
