package com.packtpub.notificationservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import java.util.function.Consumer;

@SpringBootApplication
public class NotificationservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationservicesApplication.class, args);
	}

}
