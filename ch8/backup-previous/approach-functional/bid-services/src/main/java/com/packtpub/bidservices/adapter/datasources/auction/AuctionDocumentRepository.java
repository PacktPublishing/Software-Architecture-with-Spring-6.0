package com.packtpub.bidservices.adapter.datasources.auction;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionDocumentRepository extends MongoRepository<AuctionDocument, String> {
}
