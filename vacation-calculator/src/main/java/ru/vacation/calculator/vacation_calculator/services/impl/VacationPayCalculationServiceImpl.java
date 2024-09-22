package ru.vacation.calculator.vacation_calculator.services.impl;

import org.springframework.stereotype.Service;
import ru.vacation.calculator.vacation_calculator.dto.VacationPayDto;
import ru.vacation.calculator.vacation_calculator.services.VacationPayCalculationService;

import java.time.LocalDate;
import java.time.YearMonth;

@Service
public class VacationPayCalculationServiceImpl implements VacationPayCalculationService {

    // Принятое в России среднее кол-во дней в месяце
    private static final double AVERAGE_DAYS_IN_MONTH = 29.3;

    // Кол-во месяцев в году
    private static final int NUMBER_MONTHS_IN_YEAR = 12;

    /**
     * Метод для расчета суммы отпускных
     *
     * @param vacationPayDto содержит среднюю зарплату и кол-во дней отпуска
     * @return сумма отпускных
     */
    @Override
    public Double calculateVacationPay(VacationPayDto vacationPayDto) {
        // Рассчитываем ежедневную зарплату
        double dailySalary = vacationPayDto.getAverageSalary() / AVERAGE_DAYS_IN_MONTH;
        // Рассчитываем сумму отпускных
        return dailySalary * vacationPayDto.getNumberVacationDays();
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
