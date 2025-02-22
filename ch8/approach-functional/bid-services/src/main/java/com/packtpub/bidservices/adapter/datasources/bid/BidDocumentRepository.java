package com.packtpub.bidservices.adapter.datasources.bid;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidDocumentRepository extends MongoRepository<BidDocument, String> {

}
