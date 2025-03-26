package com.packtpub.bidservices.adapter.transportlayers.restapi;

import com.packtpub.bidservices.adapter.datasources.auction.AuctionEntity;
import com.packtpub.bidservices.adapter.datasources.bid.BidEntity;
import com.packtpub.bidservices.adapter.transportlayers.restapi.dto.request.BidRequest;
import com.packtpub.bidservices.adapter.transportlayers.restapi.dto.response.BidResponse;
import com.packtpub.bidservices.internal.entity.Bid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BidMapper {
    BidMapper INSTANCE = Mappers.getMapper(BidMapper.class);

    Bid map(BidRequest bidRequest);
    BidResponse map(Bid bid);

    @Mapping(target = "auctionId", source = "auction.id")
    Bid map(BidEntity bidentity);

    @Mapping(target = "auction", source = "auctionId", qualifiedByName = "mapAuction")
    BidEntity toEntity(Bid auction);

    @Named("mapAuction")
    static AuctionEntity mapAuction(Long auctionId) {
        if (auctionId == null) return null;
        AuctionEntity auction = new AuctionEntity();
        auction.setId(auctionId);
        return auction;
    }
}
