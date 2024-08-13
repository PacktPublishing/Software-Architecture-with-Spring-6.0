package com.packtpub.bidservices.internal.repositories;

import com.packtpub.bidservices.internal.entity.Bid;

public interface BidRepository {
    Bid save(Bid bid);
}

