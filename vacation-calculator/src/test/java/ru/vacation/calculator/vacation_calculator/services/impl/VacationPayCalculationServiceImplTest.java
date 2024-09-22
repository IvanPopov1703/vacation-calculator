package ru.vacation.calculator.vacation_calculator.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.vacation.calculator.vacation_calculator.dto.VacationPayDto;
import ru.vacation.calculator.vacation_calculator.exceptions.ValidationException;
import ru.vacation.calculator.vacation_calculator.services.VacationPayCalculationService;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.vacation.calculator.vacation_calculator.services.impl.VacationPayCalculationServiceImpl.AVERAGE_DAYS_IN_MONTH;

public class VacationPayCalculationServiceImplTest {

    private VacationPayCalculationService vacationPayCalculationService;

    @BeforeEach
    public void setUp() {
        // Инициализируем объект перед запуском каждого теста
        vacationPayCalculationService = new VacationPayCalculationServiceImpl();
    }

    @Test
    public void testCalculateVacationPay_ValidCalculate() {
        // Заполняем тестовые данные
        VacationPayDto vacationPayDto = new VacationPayDto(BigDecimal.valueOf(50000.0), 5);

        // Вычисляем результат
        BigDecimal result = vacationPayCalculationService.calculateVacationPay(vacationPayDto);

        // Ожидаемый результат
        BigDecimal expected = BigDecimal.valueOf(50000.0)
                .divide(BigDecimal.valueOf(AVERAGE_DAYS_IN_MONTH), 2, HALF_UP)
                .multiply(BigDecimal.valueOf(5));

        // Сравниваем BigDecimal через compareTo (result должен быть округлён)
        assertEquals(0, expected.compareTo(result), "Расчет отпускных некорректен");
    }

    @Test
    public void testCalculateVacationPay_AverageSalaryIsZero() {
        // Заполняем тестовые данные
        VacationPayDto vacationPayDto = new VacationPayDto(ZERO, 5);

        // Ожидаем ValidationException
        assertThrows(ValidationException.class, () ->
                vacationPayCalculationService.calculateVacationPay(vacationPayDto),
                "Ожидалось исключение из-за некорректной средней зарплаты"
        );
    }

    @Test
    public void testCalculateVacationPay_VacationDaysIsZero() {
        VacationPayDto vacationPayDto = new VacationPayDto(BigDecimal.valueOf(50000), 0);

        // Ожидаем, что будет выброшено ValidationException
        assertThrows(ValidationException.class, () ->
                vacationPayCalculationService.calculateVacationPay(vacationPayDto),
                "Ожидалось исключение из-за нулевого количества дней отпуска"
        );
    }

    @Test
    public void testCalculateVacationPay_AverageSalaryIsNull() {
        // Создаем DTO с null зарплатой
        VacationPayDto vacationPayDto = new VacationPayDto(null, 5);

        // Ожидаем, что будет выброшено ValidationException
        assertThrows(ValidationException.class, () ->
                vacationPayCalculationService.calculateVacationPay(vacationPayDto),
                "Ожидалось исключение из-за null средней зарплаты"
        );
    }

}
