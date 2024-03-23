package com.example.vacationpay.web.controller;

import com.example.vacationpay.dto.VacationResponse;
import com.example.vacationpay.service.PaymentCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/vacation")
@RequiredArgsConstructor
public class VacationPaymentController {

    private final PaymentCalculatorService paymentCalculatorService;

    @GetMapping()
    public VacationResponse getVacationPay(@RequestParam int days,
                                           @RequestParam BigDecimal avgSalary,
                                           @RequestParam Optional<LocalDate> vacationStartDate) {

        if (vacationStartDate.isPresent()) {
            return paymentCalculatorService.calculatePaymentsForPeriod(vacationStartDate.get(), avgSalary, days);
        }
        return paymentCalculatorService.calculatePaymentsForDays(days, avgSalary);
    }
}
