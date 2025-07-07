package com.example.contruction.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends ConstructionException{
    public NotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }
}