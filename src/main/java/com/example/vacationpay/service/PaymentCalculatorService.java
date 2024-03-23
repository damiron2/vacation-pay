package com.example.vacationpay.service;

import com.example.vacationpay.dto.VacationResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface PaymentCalculatorService {
    VacationResponse calculatePaymentsForDays(int days, BigDecimal avgSalary);

    VacationResponse calculatePaymentsForPeriod(LocalDate vacationStartDate, BigDecimal avgSalary, int days);
}
