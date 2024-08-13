//package com.packtpub.bidservices.adapter.datasources.bid;
//
//import com.packtpub.bidservices.internal.entity.Bid;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//
//import java.util.function.Function;
//import java.util.function.Supplier;
//
//@Configuration
//public class BidProducerConfiguration {
//
//    private Bid latestBid;
//
//    @Bean
//    public Supplier<Message<Bid>> produceBid() {
//        return () -> {
//            if (latestBid != null) {
//                Message<Bid> message = MessageBuilder.withPayload(latestBid)
//                        .setHeader("eventType", "BID_CREATED")
//                        .build();
//                latestBid = null; // Reset after producing
//                return message;
//            }
//            return null;
//        };
//    }
//
//    // Method to set the latest Bid
//    public void setLatestBid(Bid bid) {
//        this.latestBid = bid;
//    }
//}
////    @Bean
////    public Function<Bid, Message<Bid>> produceBid() {
////        return bid -> {
////            if (bid != null) {
////                return MessageBuilder.withPayload(bid)
////                        .setHeader("eventType", "BID_CREATED")
////                        .build();
////            }
////            return null;
////        };
////    }
////}
