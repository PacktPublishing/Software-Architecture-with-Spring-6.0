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

//	@Bean
//	public Consumer<String> consume() {
//		return message -> {
//			System.out.println("Received message: " + message);
//			// Process the message here
//		};
//	}
//
//	@Bean
//	public Consumer<Message<String>> consume() {
//		return message -> {
//			String payload = message.getPayload();
//			String headerValue = message.getHeaders().get("myHeader", String.class);
//
//			System.out.println("Received message: " + payload);
//			System.out.println("Received header: " + headerValue);
//
//			// Process the message here, e.g., convert the payload if it's JSON, etc.
//		};
//	}
//

//	@KafkaListener(topics = "bid-added-topic", groupId = "my-consumer-group")
//	public void listen(String message) {
//		System.out.println("Received message: " + message);
//	}
}
