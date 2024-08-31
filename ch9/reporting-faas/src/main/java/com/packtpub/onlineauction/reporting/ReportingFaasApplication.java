package com.packtpub.onlineauction.reporting;

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


	@Bean
	Function<String, String> uppercase(){
		return s -> s.toUpperCase();
	}

	@Bean
	Function<Person, PersonDto> getPersonAge(){
		return s -> {
			Integer age = Period.between(s.getBirthDate(), LocalDate.now()).getYears();
			return new PersonDto(s.getName(), s.getBirthDate(), age);
		};
	}

	Supplier<Person> getPerson(){

		return Person::new;
	}


}
