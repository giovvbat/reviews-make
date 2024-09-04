package com.giovanna.reviewsmake.exception;

public class NoReviewsFoundException extends RuntimeException {
    private static final String defaultMessage = "No reviews found!";

    public NoReviewsFoundException() {
        super(defaultMessage);
    }

    public NoReviewsFoundException(String message) {
        super(message);
    }
}
