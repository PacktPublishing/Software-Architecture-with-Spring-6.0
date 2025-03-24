package com.packtpub.bidservices.adapter.datasources.bid;

import com.packtpub.bidservices.adapter.datasources.bid.kafka.BidProducer;
import com.packtpub.bidservices.adapter.transportlayers.restapi.BidMapper;
import com.packtpub.bidservices.internal.entity.Bid;
import com.packtpub.bidservices.internal.enums.BidStatus;
import com.packtpub.bidservices.internal.repositories.BidRepository;

public class BidDatasource implements BidRepository {

    private final BidJpaRepository bidJpaRepository;
    private final BidProducer bidProducer;

    public BidDatasource(BidJpaRepository bidJpaRepository, BidProducer bidProducer) {
        this.bidJpaRepository = bidJpaRepository;
        this.bidProducer = bidProducer;
    }

    @Override
    public Bid save(Bid bid) {
        BidEntity bidentity = BidMapper.INSTANCE.toEntity(bid);
        bidentity.setStatus(BidStatus.PENDING.name());
        Bid bidSaved = BidMapper.INSTANCE.map(bidJpaRepository.save(bidentity));
        bidProducer.placeBid(bidSaved);
        return bidSaved;
    }
}
