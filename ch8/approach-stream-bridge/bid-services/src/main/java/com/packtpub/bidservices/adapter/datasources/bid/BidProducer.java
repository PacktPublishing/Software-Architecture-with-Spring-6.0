package com.packtpub.bidservices.adapter.datasources.bid;

import com.packtpub.bidservices.internal.entity.Bid;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@Component
public class BidProducer {

    private final StreamBridge streamBridge;

    public BidProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void send(Bid bid) {
        Message<Bid> messageBid = MessageBuilder.withPayload(bid)
                .setHeader("eventType", "BID_CREATED")
                .build();
        try {
            boolean sent = streamBridge.send("produceBid-out-0", messageBid);
            if (!sent) {
                throw new RuntimeException("Unable to send bid event");
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to send bid event", e);
        }
    }


}

