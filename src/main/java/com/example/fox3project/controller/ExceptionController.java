package com.example.fox3project.controller;

import com.example.fox3project.exception.IsExistException;
import com.example.fox3project.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        HashMap<String, String> map = new HashMap<>();
        for (FieldError fieldError : ex.getFieldErrors()) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Your method", ex.getMethod());
        for (HttpMethod supportedHttpMethod : ex.getSupportedHttpMethods()) {
            map.put("Supported method", supportedHttpMethod.name());
        }
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValid(NotFoundException m) {
        HashMap<String, String> map = new HashMap<>();
        map.put("method", m.getMethod());
        map.put("value", String.valueOf(m.getValue()));
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IsExistException.class)
    public ResponseEntity<Map<String, String>> userIsExist(IsExistException u) {
        HashMap<String, String> map = new HashMap<>();
        map.put("method", u.getMethod());
        map.put("value", u.getValue());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
