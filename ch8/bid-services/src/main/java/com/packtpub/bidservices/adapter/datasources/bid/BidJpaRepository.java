package com.packtpub.bidservices.adapter.datasources.bid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidJpaRepository extends JpaRepository<BidEntity, Long> {

}
