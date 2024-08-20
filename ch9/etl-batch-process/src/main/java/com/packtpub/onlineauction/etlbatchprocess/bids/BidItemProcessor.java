package com.packtpub.onlineauction.etlbatchprocess.bids;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class BidItemProcessor implements ItemProcessor<BidDto, Bid> {

    @Override
    public Bid process(BidDto bidDto) throws Exception {
      return Bid.builder()
                .originalId(bidDto.getOriginalId())
                .amount(bidDto.getAmount())
                .auctionId(bidDto.getAuctionId())
                .userId(bidDto.getUserId())
                .createdAt(bidDto.getCreatedAt())
                .build();

    }
}