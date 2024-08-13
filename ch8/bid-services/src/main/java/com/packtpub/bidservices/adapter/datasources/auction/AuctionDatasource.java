package com.packtpub.bidservices.adapter.datasources.auction;

import com.packtpub.bidservices.adapter.transportlayers.restapi.AuctionMapper;
import com.packtpub.bidservices.internal.entity.Auction;
import com.packtpub.bidservices.internal.repositories.AuctionRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public class AuctionDatasource implements AuctionRepository {

    private final AuctionDocumentRepository auctionDocumentRepository;

    public AuctionDatasource(AuctionDocumentRepository auctionDocumentRepository) {
        this.auctionDocumentRepository = auctionDocumentRepository;
    }

    @Override
    public Auction save(Auction auction) {
        AuctionDocument auctionDocument = AuctionMapper.INSTANCE.toDocument(auction);
        return AuctionMapper.INSTANCE.map(auctionDocumentRepository.save(auctionDocument));
    }
}
