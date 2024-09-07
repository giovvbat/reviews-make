package com.giovanna.reviewsmake.infra.exception.review;

public class ReviewNotFoundException extends RuntimeException {
    private static final String defaultMessage = "review not found";

    public ReviewNotFoundException() {
        super(defaultMessage);
    }

    public ReviewNotFoundException(String message) {
        super(message);
    }
}
