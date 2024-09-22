package ru.vacation.calculator.vacation_calculator.services;

import ru.vacation.calculator.vacation_calculator.dto.VacationPayDto;

import java.math.BigDecimal;

public interface VacationPayCalculationService {

    // Расчет суммы отпускных
    BigDecimal calculateVacationPay(VacationPayDto vacationPayDto);
}
