package com.giovanna.reviewsmake.infra.handler.custom;

import com.giovanna.reviewsmake.dto.error.RestErrorRecordDto;
import com.giovanna.reviewsmake.infra.exception.jwt.ExpiredTokenException;
import com.giovanna.reviewsmake.infra.exception.jwt.InvalidTokenException;
import com.giovanna.reviewsmake.infra.exception.jwt.NoTokenException;
import com.giovanna.reviewsmake.infra.exception.product.NoProductsFoundException;
import com.giovanna.reviewsmake.infra.exception.product.ProductNotFoundException;
import com.giovanna.reviewsmake.infra.exception.review.NoReviewsFoundException;
import com.giovanna.reviewsmake.infra.exception.review.ReviewNotFoundException;
import com.giovanna.reviewsmake.infra.exception.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestCustomExceptionHandler {
    @ExceptionHandler({NoProductsFoundException.class, NoReviewsFoundException.class, NoUsersFoundException.class, ProductNotFoundException.class, UserNotFoundException.class, ReviewNotFoundException.class})
    public ResponseEntity<RestErrorRecordDto> handleNotFoundException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestErrorRecordDto(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler({UsernameAlreadyTakenException.class, EmailAlreadyTakenException.class, SamePasswordException.class})
    public ResponseEntity<RestErrorRecordDto> handleConflictException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new RestErrorRecordDto(HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler({UnauthorizedCredentialsException.class, InvalidTokenException.class, ExpiredTokenException.class, NoTokenException.class})
    public ResponseEntity<RestErrorRecordDto> handleUnauthorizedException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new RestErrorRecordDto(HttpStatus.UNAUTHORIZED, ex.getMessage()));
    }
}
