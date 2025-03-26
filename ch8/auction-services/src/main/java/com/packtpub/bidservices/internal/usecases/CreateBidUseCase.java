package com.packtpub.bidservices.internal.usecases;

import com.packtpub.bidservices.internal.entity.Bid;
import com.packtpub.bidservices.internal.repositories.BidRepository;

public class CreateBidUseCase {
    private final BidRepository bidRepository;

    public CreateBidUseCase(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    public Bid execute(Bid bid) {
        return bidRepository.save(bid);
    }

}