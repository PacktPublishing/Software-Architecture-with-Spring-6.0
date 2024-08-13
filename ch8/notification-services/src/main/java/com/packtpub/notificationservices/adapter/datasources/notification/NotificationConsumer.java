package com.packtpub.notificationservices.adapter.datasources.notification;

import com.packtpub.notificationservices.internal.entity.Bid;
import com.packtpub.notificationservices.internal.usecases.CreateNotificationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;


@Configuration
public class NotificationConsumer {

    private final CreateNotificationUseCase createNotificationUseCase;

    public NotificationConsumer(CreateNotificationUseCase createNotificationUseCase) {
        this.createNotificationUseCase = createNotificationUseCase;
    }

    @Bean
    public Consumer<Message<Bid>> consume() {
        return message -> {

            // Get the Bid payload from the message
            Bid bid = message.getPayload();

            // Get a specific header (e.g., "myHeader")
            //String headerValue = message.getHeaders().get("myHeader", String.class);

            // Process the Bid object and the header
            System.out.println("Received Bid: " + bid.toString());
//            System.out.println("Received header: " + headerValue);

            createNotificationUseCase.execute(bid);

        };
    }
}

