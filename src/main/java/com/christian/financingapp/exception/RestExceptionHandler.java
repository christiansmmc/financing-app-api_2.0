package com.christian.financingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    private ResponseEntity<RestErrorMessage> emailAlreadyExistsHandler(EmailAlreadyExistsException e) {
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<RestErrorMessage> genericExceptionHandler(Exception e) {
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }
}
