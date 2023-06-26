package com.insearchofknowledge.bugTracker.exception;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage() + " (provided by GlobalExceptionHandler)");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + " (provided by GlobalExceptionHandler)");
    }

    @ExceptionHandler(NoSuchFieldException.class)
    public ResponseEntity<String> handleNoSuchFieldException(NoSuchFieldException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + " (provided by GlobalExceptionHandler)");
    }

    @ExceptionHandler(ClassCastException.class)
    public ResponseEntity<String> handleClassCastException(ClassCastException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + " (provided by GlobalExceptionHandler)");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> handleExpiredJwtException(ExpiredJwtException e) {
        String errorMessage = e.getMessage() + " (provided by GlobalExceptionHandler)";
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
    }

//    @ExceptionHandler(ExpiredJwtException.class)
//    public ResponseEntity<ErrorResponse> handleJwtExpiredException(ExpiredJwtException ex) {
//        ErrorResponse errorResponse = new ErrorResponse("JWT token has expired");
//        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
//    }
}
