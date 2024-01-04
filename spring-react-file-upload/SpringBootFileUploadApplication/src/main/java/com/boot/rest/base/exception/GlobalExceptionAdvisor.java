package com.boot.rest.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionAdvisor {

    @ExceptionHandler(FileNotSupportedException.class)
    public ResponseEntity<Map<String,Object>> handleFileNotSupportException(FileNotSupportedException ex){
        return new ResponseEntity<>(Map.of("message",ex.getMessage(),"stateCode",409,"success",false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleFileNotFoundException(FileNotFoundException ex){
        return new ResponseEntity<>(Map.of("message",ex.getMessage(),"stateCode",404,"success",true), HttpStatus.NOT_FOUND);
    }
}
