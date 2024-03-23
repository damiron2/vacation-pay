package com.example.vacationpay;

import com.example.vacationpay.dto.VacationResponse;
import com.example.vacationpay.exception.IllegalArgException;
import com.example.vacationpay.web.controller.VacationPaymentController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class VacationPayApplicationTests {

    @Autowired
    private VacationPaymentController paymentController;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(paymentController);
    }

    @Test
    void calculatePaymentsForDays() {
        BigDecimal averageSalary = BigDecimal.valueOf(1000);
        int vacationDays = 1;
        BigDecimal expectedFeeResult = BigDecimal.valueOf(34.13d);
        VacationResponse calculateResult = paymentController.getVacationPay(vacationDays,averageSalary, Optional.empty());
        Assertions.assertEquals(calculateResult.getVacationFee(),expectedFeeResult);
        Assertions.assertEquals(calculateResult.getMessage(),"Сумма отпускных");
    }

    @Test
    void calculatePaymentsForPeriodWithoutHolidays(){
        BigDecimal averageSalary = BigDecimal.valueOf(1000);
        int vacationDays = 1;
        LocalDate vacationStart = LocalDate.of(2024,3,1);
        BigDecimal expectedFeeResult = BigDecimal.valueOf(34.13d);
        VacationResponse calculateResult = paymentController.getVacationPay(vacationDays,averageSalary, Optional.of(vacationStart));
        Assertions.assertEquals(calculateResult.getVacationFee(),expectedFeeResult);
        Assertions.assertEquals(calculateResult.getMessage(),"Сумма отпускных");
    }

    @Test
    void calculatePaymentsForPeriodWithHolidays(){
        BigDecimal averageSalary = BigDecimal.valueOf(1000);
        int vacationDays = 1;
        LocalDate vacationStart = LocalDate.of(2024,5,1);
        BigDecimal expectedFeeResult = BigDecimal.valueOf(0,2);
        VacationResponse calculateResult = paymentController.getVacationPay(vacationDays,averageSalary, Optional.of(vacationStart));
        Assertions.assertEquals(calculateResult.getVacationFee(),expectedFeeResult);
        Assertions.assertEquals(calculateResult.getMessage(),"Сумма отпускных");
    }

    @Test
    void calculatePaymentsForPeriodWithIllegalDaysValue(){
        BigDecimal averageSalary = BigDecimal.valueOf(1000);
        int vacationDays = -1;
        Throwable exception = assertThrows(IllegalArgException.class,()-> paymentController.getVacationPay(vacationDays,averageSalary, Optional.empty()));
        assertEquals(exception.getMessage(),"Value of days and avgSalary should be greater or equals 0");
    }

    @Test
    void calculatePaymentsForPeriodWithIllegalAvgSalaryValue(){
        BigDecimal averageSalary = BigDecimal.valueOf(1000);
        int vacationDays = -1;
        Throwable exception = assertThrows(IllegalArgException.class,()-> paymentController.getVacationPay(vacationDays,averageSalary, Optional.empty()));
        assertEquals(exception.getMessage(),"Value of days and avgSalary should be greater or equals 0");
    }



}
