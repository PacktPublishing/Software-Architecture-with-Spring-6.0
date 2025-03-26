package com.packtpub.bidservices.adapter.datasources.auction;

import com.packtpub.bidservices.adapter.transportlayers.restapi.AuctionMapper;
import com.packtpub.bidservices.internal.entity.Auction;
import com.packtpub.bidservices.internal.repositories.AuctionRepository;

public class AuctionDatasource implements AuctionRepository {

    private final AuctionJpaRepository auctionJpaRepository;

    public AuctionDatasource(AuctionJpaRepository auctionJpaRepository) {
        this.auctionJpaRepository = auctionJpaRepository;
    }

    @Override
    public Auction save(Auction auction) {
        AuctionEntity auctionEntity = AuctionMapper.INSTANCE.toEntity(auction);
        return AuctionMapper.INSTANCE.map(auctionJpaRepository.save(auctionEntity));
    }
}
