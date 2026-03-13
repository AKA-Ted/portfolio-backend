package com.site.global.exception;

import com.site.global.dto.GlobalResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GlobalResponse<Object>> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {

        GlobalResponse<Object> errorResponse = new GlobalResponse<>(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<GlobalResponse<Object>> handleResourceConflictException(
            ResourceConflictException ex, WebRequest request) {
        GlobalResponse<Object> errorResponse = new GlobalResponse<>(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<GlobalResponse<Object>> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex, WebRequest request) {

        String message = "Data conflict";

        GlobalResponse<Object> errorResponse = new GlobalResponse<>(
                HttpStatus.CONFLICT.value(), // 409
                message,
                null
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponse<Object>> handleGlobalException(
            Exception ex, WebRequest request) {
        GlobalResponse<Object> errorResponse = new GlobalResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), // 500
                "INTERNAL SERVER ERROR:  " + ex.getMessage(),
                null
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
