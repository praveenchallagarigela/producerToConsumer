package com.praveentechie.producertoconsumer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public Map<String,String> handleProductNotFoundException(ProductNotFoundException ex){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("error message", ex.getMessage());
        return errorMap;
    }
}
