package com.packtpub.bidservices.internal.repositories;

import com.packtpub.bidservices.internal.entity.Auction;

public interface AuctionRepository {
    Auction save(Auction auction);
}

