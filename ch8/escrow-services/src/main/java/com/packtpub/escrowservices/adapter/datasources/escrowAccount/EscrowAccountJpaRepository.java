package com.packtpub.escrowservices.adapter.datasources.escrowAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscrowAccountJpaRepository extends JpaRepository<EscrowAccountEntity, Long> {
}
