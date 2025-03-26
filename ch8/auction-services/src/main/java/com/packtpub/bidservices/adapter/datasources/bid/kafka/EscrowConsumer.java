package com.packtpub.bidservices.adapter.datasources.bid.kafka;

import com.packtpub.bidservices.adapter.transportlayers.restapi.dto.event.BidEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EscrowConsumer {

    @KafkaListener(topics = "escrows-topic", groupId = "bid-group")
    public void escrowsConsumer(BidEvent bidEvent) {
        log.info("Escrow {} processing from escrow topic!", bidEvent.getId());

        if("ACCEPTED".equals(bidEvent.getStatus())) {
            log.info("Bid accepted!");
        }else {
            log.info("Bid rejected!");
        }
        log.info("Escrow {} processed from escrow topic!", bidEvent.getId());

        // NOW WE COULD SEND A MESSAGE TO TOPIC NOTIFICATIONS, SO OTHER SERVICE
        // WOULD PROCESS IT AND NOTIFY THE USER

        log.info("SEND BID {} STATUS TO THE NOTIFICATION TOPIC!", bidEvent.getId());



    }
}