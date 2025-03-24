package com.packtpub.escrowservices.adapter.datasources.escrowTransaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface EscrowTransactionJpaRepository extends JpaRepository<EscrowTransactionEntity, Long> {
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM EscrowTransactionEntity e WHERE e.userId = :userId AND e.status = 'RESERVED'")
    BigDecimal findTotalReservationsById(@Param("userId") Long userId);
}
