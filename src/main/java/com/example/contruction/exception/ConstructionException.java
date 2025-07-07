package com.example.contruction.exception;

import org.springframework.http.HttpStatus;

public class ConstructionException extends RuntimeException {
    private HttpStatus status;

    public ConstructionException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return this.status;
    }
}
