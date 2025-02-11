package com.vikku.OrderService.core.errorhandling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class OrdersServiceErrorHandler {

    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex, WebRequest webRequest) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest webRequest) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
