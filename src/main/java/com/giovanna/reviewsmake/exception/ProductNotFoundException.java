package com.giovanna.reviewsmake.exception;

public class ProductNotFoundException extends RuntimeException {
    private static final String defaultMessage = "Product not found!";

    public ProductNotFoundException() {
        super(defaultMessage);
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
