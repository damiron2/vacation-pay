package com.example.vacationpay.web.controller;


import com.example.vacationpay.exception.ExceptionBody;
import com.example.vacationpay.exception.IllegalArgException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(IllegalArgException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleIllegalArgException(IllegalArgException e) {
        return new ExceptionBody(e.getMessage());
    }
}
