package com.example.vacationpay.exception;

import lombok.Data;

@Data
public class ExceptionBody {
    private String message;

    public ExceptionBody(String message) {
        this.message = message;
    }
}
