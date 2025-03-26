package com.packtpub.bidservices.adapter.datasources.bid.kafka;

import com.packtpub.bidservices.internal.entity.Bid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class BidProducer {

    private final KafkaTemplate<String, Bid> kafkaTemplate;

    private static final String BIDS_TOPIC = "bids-topic";
    private static final String KEY_BIDS_TOPIC = "key-bids";

    public BidProducer(KafkaTemplate<String, Bid> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void placeBid(Bid bid) {
        log.info("Place bid: {}", bid);
        kafkaTemplate.send(BIDS_TOPIC, KEY_BIDS_TOPIC, bid);
        log.info("Bid {} sent to bids topic to validate user funds!", bid.getId());
    }
}

