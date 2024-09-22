package ru.vacation.calculator.vacation_calculator.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.vacation.calculator.vacation_calculator.dto.VacationPayDto;
import ru.vacation.calculator.vacation_calculator.services.VacationPayCalculationService;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class VacationPayCalculationRestController implements VacationPayCalculationControllerApi {

    private final VacationPayCalculationService vacationPayCalculationService;

    @Override
    public BigDecimal calculateVacationPay(VacationPayDto vacationPayDto) {
        return vacationPayCalculationService.calculateVacationPay(vacationPayDto);
    }
}