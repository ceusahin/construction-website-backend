package com.example.contruction.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConstructionErrorResponse {
    private String message;
    private int status;
    private long timestamp;
}