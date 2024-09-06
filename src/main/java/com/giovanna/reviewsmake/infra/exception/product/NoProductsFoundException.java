package com.giovanna.reviewsmake.infra.exception.product;

public class NoProductsFoundException extends RuntimeException {
    private static final String defaultMessage = "No products found!";

    public NoProductsFoundException() {
        super(defaultMessage);
    }

    public NoProductsFoundException(String message) {
        super(message);
    }
}
