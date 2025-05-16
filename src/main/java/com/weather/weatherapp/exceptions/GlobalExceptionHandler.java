package com.weather.weatherapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.weather.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ApiResponse> dataNotFoundExceptionHandler(DataNotFoundException e){

        ApiResponse res = new ApiResponse();
        res.setMessage(e.getMessage());
        res.setStatus(HttpStatus.NOT_FOUND);

        return new ResponseEntity<ApiResponse>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse> MissingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e){
        ApiResponse res = new ApiResponse();
        res.setMessage(e.getMessage());
        res.setStatus(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<ApiResponse>(res, HttpStatus.BAD_REQUEST);        
    }
}
