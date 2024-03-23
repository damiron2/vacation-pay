package com.example.vacationpay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class VacationResponse {

    private String message;
    private BigDecimal vacationFee;
}
