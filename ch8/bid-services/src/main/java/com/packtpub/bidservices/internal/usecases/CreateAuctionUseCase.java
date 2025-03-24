package com.packtpub.bidservices.internal.usecases;

import com.packtpub.bidservices.internal.entity.Auction;
import com.packtpub.bidservices.internal.repositories.AuctionRepository;

public class CreateAuctionUseCase {
    private final AuctionRepository auctionRepository;

    public CreateAuctionUseCase(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }


    public Auction execute(Auction auction) {
        return auctionRepository.save(auction);
    }

}