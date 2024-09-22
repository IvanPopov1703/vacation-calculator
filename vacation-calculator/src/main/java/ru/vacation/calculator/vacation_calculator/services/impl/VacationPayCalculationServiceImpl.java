package ru.vacation.calculator.vacation_calculator.services.impl;

import org.springframework.stereotype.Service;
import ru.vacation.calculator.vacation_calculator.dto.VacationPayDto;
import ru.vacation.calculator.vacation_calculator.exceptions.ValidationException;
import ru.vacation.calculator.vacation_calculator.services.VacationPayCalculationService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

import static java.math.RoundingMode.HALF_UP;

@Service
public class VacationPayCalculationServiceImpl implements VacationPayCalculationService {

    // Принятое в России среднее кол-во дней в месяце
    public static final double AVERAGE_DAYS_IN_MONTH = 29.3;

    // Кол-во месяцев в году
    public static final int NUMBER_MONTHS_IN_YEAR = 12;

    /**
     * Метод для расчета суммы отпускных
     *
     * @param vacationPayDto содержит среднюю зарплату и кол-во дней отпуска
     * @return сумма отпускных
     */
    @Override
    public BigDecimal calculateVacationPay(VacationPayDto vacationPayDto) {
        // Перед вычислением валидируем входные данные
        validateVacationPayDto(vacationPayDto);

        // Рассчитываем ежедневную зарплату
        BigDecimal dailySalary = vacationPayDto.getAverageSalary().divide(
                BigDecimal.valueOf(AVERAGE_DAYS_IN_MONTH),
                2,
                HALF_UP
        );

        // Рассчитываем сумму отпускных
        BigDecimal vacationPay = dailySalary.multiply(BigDecimal.valueOf(vacationPayDto.getNumberVacationDays()));

        // Возвращаем округлённый результат
        return vacationPay.setScale(2, HALF_UP);
    }

    /**
     * Метод для валидации объекта с входными данными для расчета суммы отпускных
     *
     * @param vacationPayDto валидируемый объект
     */
    private void validateVacationPayDto(VacationPayDto vacationPayDto) {
        if (vacationPayDto.getNumberVacationDays() == 0) {
            throw new ValidationException("Кол-во дней отпуска задано некорректно");
        }

        if (vacationPayDto.getAverageSalary() == null
                || vacationPayDto.getAverageSalary().compareTo(BigDecimal.ZERO) == 0
        ) {
            throw new ValidationException("Средняя зарплата задана некорректно");
        }
    }


    /**
     * Метод для вычисления среднего кол-ва дней в месяце для текущего года
     * В России принято значение 29.3, данным метод оставил, на случай расширения приложения
     *
     * @return среднее кол-во дней
     */
    private double getAverageNumDaysInMonthInCurrentYear() {
        // Определяем текущий год
        int currYear = LocalDate.now().getYear();
        int numberOfDays = 0;

        for (int monthNum = 1; monthNum <= NUMBER_MONTHS_IN_YEAR; monthNum++) {
            // Вычисляем кол-во дней в каждом месяце и суммируем
            numberOfDays += YearMonth.of(currYear, monthNum).lengthOfMonth();
        }

        // Вычисляем среднее кол-во дней в месяце
        return (double) numberOfDays / NUMBER_MONTHS_IN_YEAR;
    }
}
