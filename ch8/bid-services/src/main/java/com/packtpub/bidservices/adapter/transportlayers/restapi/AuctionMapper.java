package com.packtpub.bidservices.adapter.transportlayers.restapi;

import com.packtpub.bidservices.adapter.datasources.auction.AuctionDocument;
import com.packtpub.bidservices.adapter.transportlayers.restapi.dto.request.AuctionRequest;
import com.packtpub.bidservices.adapter.transportlayers.restapi.dto.response.AuctionResponse;
import com.packtpub.bidservices.internal.entity.Auction;
import com.packtpub.bidservices.internal.entity.Bid;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuctionMapper {
    AuctionMapper INSTANCE = Mappers.getMapper(AuctionMapper.class);

    Auction map(AuctionRequest auctionRequest);
    AuctionResponse map(Auction auction);
    Auction map(AuctionDocument auctionDocument);
    AuctionDocument toDocument(Auction auction);
}
