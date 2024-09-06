package com.giovanna.reviewsmake.infra.exception.product;

public class ProductNotFoundException extends RuntimeException {
    private static final String defaultMessage = "Product not found!";

    public ProductNotFoundException() {
        super(defaultMessage);
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
