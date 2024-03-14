package com.tunebaker.farm.exception;

import org.springframework.dao.DataIntegrityViolationException;
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
        int status;
        switch (e) {
            case DataIntegrityViolationException ex -> status = 400;
            case UserAlreadyExistsException ex -> status = 400;
            case PasswordsNotEqualException ex -> status = 400;
            default -> status = 500;
        }


        AppErrorInfo errorInfo = new AppErrorInfo().status(status)
                                                   .date(new Date())
                                                   .exception(e.getClass().getName())
                                                   .message(e.getMessage());
        return ResponseEntity.status(status).body(errorInfo.toString());
    }
}
