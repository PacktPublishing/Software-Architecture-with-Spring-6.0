package com.packtpub.bidservices.adapter.datasources.bid;

import com.packtpub.bidservices.adapter.transportlayers.restapi.BidMapper;
import com.packtpub.bidservices.internal.entity.Bid;
import com.packtpub.bidservices.internal.repositories.BidRepository;

public class BidDatasource implements BidRepository {

    private final BidDocumentRepository bidDocumentRepository;
    private final BidProducer bidProducer;
//    private final BidProducerConfiguration bidProducerConfiguration;

    public BidDatasource(BidDocumentRepository bidDocumentRepository, BidProducer bidProducer) {
        this.bidDocumentRepository = bidDocumentRepository;
        this.bidProducer = bidProducer;
    }

    @Override
    public Bid save(Bid bid) {
        bidProducer.send(bid);

        //        bidProducerConfiguration.setLatestBid(bid);

        BidDocument bidDocument = BidMapper.INSTANCE.toDocument(bid);
        Bid bidSaved = BidMapper.INSTANCE.map(bidDocumentRepository.save(bidDocument));
        bidProducer.send(bidSaved);
        return bidSaved;
    }
}
