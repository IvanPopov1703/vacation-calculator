package ru.vacation.calculator.vacation_calculator.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vacation.calculator.vacation_calculator.dto.VacationPayDto;

@Tag(name = "VacationPayCalculation", description = "Контроллер для расчета отпускных")
@RequestMapping("/api/v1")
public interface VacationPayCalculationControllerApi {

    @Operation(
            summary = "Рассчитать сумму отпускных",
            description = "Рассчитывает сумму отпускных по средней ЗП и кол-ву дней отпуска"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "400", description = "Неверные данные пользователя"),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере")
    })
    @PostMapping("/calculate")
    double calculateVacationPay(@RequestBody VacationPayDto vacationPayDto);

}
