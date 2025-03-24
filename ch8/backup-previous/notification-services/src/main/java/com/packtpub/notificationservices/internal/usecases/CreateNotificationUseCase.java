package com.packtpub.notificationservices.internal.usecases;

import com.packtpub.notificationservices.internal.entity.Bid;
import com.packtpub.notificationservices.internal.entity.Notification;
import com.packtpub.notificationservices.internal.repositories.NotificationRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CreateNotificationUseCase {
    private final NotificationRepository notificationRepository;

    public CreateNotificationUseCase(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification execute(Bid bid) {

        // Here we would have to get the product description and the emails
        // of users whose participated at the auction. As it is an example,
        // we use hardcoded.

        String auctionId = bid.getAuctionId();
        List<String> emails = List.of("wandersonxs@auction.com");

        Notification notification = new Notification(null, auctionId, emails);

        return notificationRepository.save(notification);
    }

}