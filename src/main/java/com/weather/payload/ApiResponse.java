package com.weather.payload;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiResponse {
    private HttpStatus status;
    private String message;
}
