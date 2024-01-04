package com.rest.api.project.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionAdviser {

    @ExceptionHandler(FileNotSupportedException.class)
    public ResponseEntity<Map<String,Object>> handleFileNotSupportException(FileNotSupportedException ex){
        return new ResponseEntity<>(Map.of("message",ex.getMessage(),"stateCode",409,"success",false), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        Map<String, Object> responseMap = Map.of("statusCode", 404, "message", ex.getMessage(), "path", request.getContextPath(), "date", new Date());
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleFileNotFoundException(FileNotFoundException ex){
        return new ResponseEntity<>(Map.of("message",ex.getMessage(),"stateCode",404,"success",true), HttpStatus.NOT_FOUND);
    }
}
