package ru.vacation.calculator.vacation_calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationPayDto {

    // Средняя зарплата за 12 месяцев
    private BigDecimal averageSalary;

    // Кол-во дней отпуска
    private int numberVacationDays;
}
