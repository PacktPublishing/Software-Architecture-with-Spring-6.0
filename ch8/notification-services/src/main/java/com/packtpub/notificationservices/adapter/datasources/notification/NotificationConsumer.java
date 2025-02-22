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

            Bid bid = message.getPayload();
            System.out.println("Received Bid: " + bid.toString());
            createNotificationUseCase.execute(bid);

        };
    }
}

