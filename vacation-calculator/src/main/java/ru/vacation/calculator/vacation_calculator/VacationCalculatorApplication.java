package ru.vacation.calculator.vacation_calculator;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Расчет заработной платы",
                version = "1.0.0",
                description = "API для расчета заработной платы",
                contact = @Contact(
                        name = "Иван Попов",
                        email = "popov1703.ivan@ya.ru"
                )
        )
)
public class VacationCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(VacationCalculatorApplication.class, args);
    }

}
