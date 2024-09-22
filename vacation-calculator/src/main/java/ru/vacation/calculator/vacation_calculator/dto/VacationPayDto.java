package ru.vacation.calculator.vacation_calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationPayDto {

    // Средняя зарплата за 12 месяцев
    private double averageSalary;

    // Кол-во дней отпуска
    private int numberVacationDays;
}
