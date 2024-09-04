package com.giovanna.reviewsmake.handler.custom;

import com.giovanna.reviewsmake.exception.UnauthorizedCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UnauthorizedCredentialsExceptionHandler {
    @ExceptionHandler(UnauthorizedCredentialsException.class)
    public ResponseEntity<String> handleUnauthorizedCredentialsException(UnauthorizedCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
}
