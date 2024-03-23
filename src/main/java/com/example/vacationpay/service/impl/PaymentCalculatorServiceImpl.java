package com.example.vacationpay.service.impl;

import com.example.vacationpay.data.HolidaysData;
import com.example.vacationpay.dto.VacationResponse;
import com.example.vacationpay.exception.IllegalArgException;
import com.example.vacationpay.service.PaymentCalculatorService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Service
public class PaymentCalculatorServiceImpl implements PaymentCalculatorService {

    private final BigDecimal avgDaysPerMonth = BigDecimal.valueOf(29.3);

    @Override
    public VacationResponse calculatePaymentsForDays(int days, BigDecimal avgSalary) {

        if (days < 0 || avgSalary.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new IllegalArgException("Value of days and avgSalary should be greater or equals 0");
        }
        BigDecimal vacationFee = avgSalary.divide(avgDaysPerMonth, 2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(days));
        return new VacationResponse("Сумма отпускных", vacationFee);
    }

    @Override
    public VacationResponse calculatePaymentsForPeriod(LocalDate vacationStartDate, BigDecimal avgSalary, int days) {
        int holidays = 0;
        for (int i = 0; i < days; i++) {
            if (HolidaysData.holidaysDays.get(vacationStartDate.
                            getMonth()
                            .toString())
                    .contains(vacationStartDate.getDayOfMonth())) {
                holidays++;
                vacationStartDate = ChronoUnit.DAYS.addTo(vacationStartDate, 1);
            } else {
                vacationStartDate = ChronoUnit.DAYS.addTo(vacationStartDate, 1);
            }
        }
        return calculatePaymentsForDays(days - holidays, avgSalary);
    }


}
