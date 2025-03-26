package com.packtpub.bidservices.adapter.datasources.auction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionJpaRepository extends JpaRepository<AuctionEntity, Long> {
}
