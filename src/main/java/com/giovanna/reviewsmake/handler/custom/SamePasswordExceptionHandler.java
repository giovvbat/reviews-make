package com.giovanna.reviewsmake.handler.custom;

import com.giovanna.reviewsmake.exception.EmailAlreadyTakenException;
import com.giovanna.reviewsmake.exception.SamePasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SamePasswordExceptionHandler {
    @ExceptionHandler(SamePasswordException.class)
    public ResponseEntity<String> handleSamePasswordException(SamePasswordException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}