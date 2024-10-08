package com.giovanna.reviewsmake.infra.handler.custom;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.giovanna.reviewsmake.domain.dto.error.RestErrorRecordDto;
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
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestErrorRecordDto(HttpStatus.NOT_FOUND.toString(), ex.getMessage()));
    }

    @ExceptionHandler({UsernameAlreadyTakenException.class, EmailAlreadyTakenException.class})
    public ResponseEntity<RestErrorRecordDto> handleConflictException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new RestErrorRecordDto(HttpStatus.CONFLICT.toString(), ex.getMessage()));
    }

    @ExceptionHandler(SamePasswordException.class)
    public ResponseEntity<RestErrorRecordDto> handleBadRequestException(SamePasswordException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestErrorRecordDto(HttpStatus.CONFLICT.toString(), ex.getMessage()));
    }

    @ExceptionHandler({UnauthorizedCredentialsException.class, UserNotLoggedException.class})
    public ResponseEntity<RestErrorRecordDto> handleUnauthorizedException(UnauthorizedCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new RestErrorRecordDto(HttpStatus.UNAUTHORIZED.toString(), ex.getMessage()));
    }

    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity<RestErrorRecordDto> handleJWTCreationException(JWTCreationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestErrorRecordDto(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "unexpected error ocurred"));
    }
}
