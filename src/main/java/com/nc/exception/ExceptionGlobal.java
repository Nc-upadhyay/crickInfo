package com.nc.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionGlobal extends IllegalArgumentException {
    @ExceptionHandler(IllegalArgumentException.class)
    public void illegaArgumentException() {
        System.out.println("There is someting illegal we will see it later.");
    }
}
