package com.packtpub.escrowservices.adapter.datasources.escrowTransaction.kafka;

import com.packtpub.escrowservices.adapter.transportlayers.restapi.dto.event.BidEvent;
import com.packtpub.escrowservices.adapter.transportlayers.restapi.dto.event.EscrowEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class EscrowProducer {

    private final KafkaTemplate<String, BidEvent> kafkaTemplate;

    public EscrowProducer(KafkaTemplate<String, BidEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void placeEscrow(BidEvent bidEvent) {
        kafkaTemplate.send("escrows-topic", "key-escrow", bidEvent);
        log.info("Escrow {} sent to escrow   topic!", bidEvent.getId());
    }
}

