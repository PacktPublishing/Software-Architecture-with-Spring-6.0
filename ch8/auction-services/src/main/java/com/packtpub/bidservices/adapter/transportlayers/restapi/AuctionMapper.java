package com.packtpub.bidservices.adapter.transportlayers.restapi;

import com.packtpub.bidservices.adapter.datasources.auction.AuctionEntity;
import com.packtpub.bidservices.adapter.transportlayers.restapi.dto.request.AuctionRequest;
import com.packtpub.bidservices.adapter.transportlayers.restapi.dto.response.AuctionResponse;
import com.packtpub.bidservices.internal.entity.Auction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuctionMapper {
    AuctionMapper INSTANCE = Mappers.getMapper(AuctionMapper.class);

    Auction map(AuctionRequest auctionRequest);
    AuctionResponse map(Auction auction);
    Auction map(AuctionEntity auctionEntity);
    AuctionEntity toEntity(Auction auction);
}
