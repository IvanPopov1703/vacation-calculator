package ru.vacation.calculator.vacation_calculator.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.vacation.calculator.vacation_calculator.exceptions.ValidationException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = {ValidationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String resourceNotFoundException(ValidationException ex) {
        return ex.getMessage();
    }
}
