package ru.vacation.calculator.vacation_calculator.services;

import ru.vacation.calculator.vacation_calculator.dto.VacationPayDto;

public interface VacationPayCalculationService {

    // Расчет суммы отпускных
    Double calculateVacationPay(VacationPayDto vacationPayDto);
}
