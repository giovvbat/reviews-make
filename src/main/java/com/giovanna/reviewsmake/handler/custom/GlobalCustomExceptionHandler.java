package com.giovanna.reviewsmake.handler.custom;

import com.giovanna.reviewsmake.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalCustomExceptionHandler {
    @ExceptionHandler({NoProductsFoundException.class, NoReviewsFoundException.class, NoUsersFoundException.class, ProductNotFoundException.class, UserNotFoundException.class, ReviewNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({UsernameAlreadyTakenException.class, EmailAlreadyTakenException.class})
    public ResponseEntity<String> handleAlreadyTakenException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(SamePasswordException.class)
    public ResponseEntity<String> handleSamePasswordException(SamePasswordException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedCredentialsException.class)
    public ResponseEntity<String> handleUnauthorizedCredentialsException(UnauthorizedCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
}
