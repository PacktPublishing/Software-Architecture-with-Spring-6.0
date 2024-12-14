package com.packtpub.onlineauction.alerting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class ReportingFaasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportingFaasApplication.class, args);
	}

}
