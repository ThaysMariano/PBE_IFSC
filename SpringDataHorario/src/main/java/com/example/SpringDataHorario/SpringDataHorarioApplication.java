package com.example.SpringDataHorario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class SpringDataHorarioApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringDataHorarioApplication.class, args);
	}



}
