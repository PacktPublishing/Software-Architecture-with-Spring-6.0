package com.packtpub.bidservices.adapter.transportlayers.restapi;

import com.packtpub.bidservices.adapter.datasources.bid.BidDocument;
import com.packtpub.bidservices.adapter.transportlayers.restapi.dto.request.BidRequest;
import com.packtpub.bidservices.adapter.transportlayers.restapi.dto.response.AuctionResponse;
import com.packtpub.bidservices.adapter.transportlayers.restapi.dto.response.BidResponse;
import com.packtpub.bidservices.internal.entity.Bid;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BidMapper {
    BidMapper INSTANCE = Mappers.getMapper(BidMapper.class);

    Bid map(BidRequest bidRequest);
    BidResponse map(Bid bid);
    Bid map(BidDocument bidDocument);
    BidDocument toDocument(Bid auction);

}
