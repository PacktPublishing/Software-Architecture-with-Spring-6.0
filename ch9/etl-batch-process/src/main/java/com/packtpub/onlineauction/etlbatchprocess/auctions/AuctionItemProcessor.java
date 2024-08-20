package com.packtpub.onlineauction.etlbatchprocess.auctions;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class AuctionItemProcessor implements ItemProcessor<AuctionDto, Auction> {

    public Auction process(AuctionDto auctionDto) throws Exception {
        return Auction.builder()
                .originalId(auctionDto.getOriginalId())
                .description(auctionDto.getDescription())
                .maxbid(auctionDto.getMaxbid())
                .minbid(auctionDto.getMinbid())
                .productid(auctionDto.getProductid())
                .isActive(auctionDto.isActive())
                .createdAt(auctionDto.getCreatedAt())
                .build();
    }
}