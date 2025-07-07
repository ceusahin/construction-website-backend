package com.example.contruction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ConstructionErrorResponse> handleException(ConstructionException constructionException) {
        ConstructionErrorResponse constructionErrorResponse = new ConstructionErrorResponse(
                constructionException.getMessage(),
                constructionException.getStatus().value(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(constructionErrorResponse, constructionException.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ConstructionErrorResponse> handleException(Exception exception) {
        ConstructionErrorResponse constructionErrorResponse = new ConstructionErrorResponse(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(constructionErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}