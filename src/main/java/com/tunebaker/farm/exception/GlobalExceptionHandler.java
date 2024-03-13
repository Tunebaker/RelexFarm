package com.tunebaker.farm.exception;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> handleException(RuntimeException e) {
        AppErrorInfo errorInfo = new AppErrorInfo().status(500)
                                                   .date(new Date())
                                                   .exception(e.getClass().getName())
                                                   .message(e.getMessage());
        return ResponseEntity.status(500).body(errorInfo.toString());
    }
}
