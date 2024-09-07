package com.giovanna.reviewsmake.infra.exception.review;

public class NoReviewsFoundException extends RuntimeException {
    private static final String defaultMessage = "no reviews found";

    public NoReviewsFoundException() {
        super(defaultMessage);
    }

    public NoReviewsFoundException(String message) {
        super(message);
    }
}
