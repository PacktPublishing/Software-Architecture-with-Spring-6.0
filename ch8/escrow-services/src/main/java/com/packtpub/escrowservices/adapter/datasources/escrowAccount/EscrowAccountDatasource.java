package com.packtpub.escrowservices.adapter.datasources.escrowAccount;

import com.packtpub.escrowservices.adapter.transportlayers.restapi.EscrowAccountMapper;
import com.packtpub.escrowservices.internal.entity.EscrowAccount;
import com.packtpub.escrowservices.internal.repositories.EscrowAccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class EscrowAccountDatasource implements EscrowAccountRepository {

    private final EscrowAccountJpaRepository escrowAccountJpaRepository;

    public EscrowAccountDatasource(EscrowAccountJpaRepository escrowAccountJpaRepository) {
        this.escrowAccountJpaRepository = escrowAccountJpaRepository;
    }

    @Override
    public EscrowAccount save(EscrowAccount escrowAccount) {
        EscrowAccountEntity escrowAccountEntity = EscrowAccountMapper.INSTANCE.toDocument(escrowAccount);
        return EscrowAccountMapper.INSTANCE.map(escrowAccountJpaRepository.save(escrowAccountEntity));
    }

    @Override
    public BigDecimal findById(Long userId) {
        Optional<EscrowAccountEntity> escrowAccountEntity = escrowAccountJpaRepository.findById(userId);
        return escrowAccountEntity.map(EscrowAccountEntity::getAvailableFunds).orElse(null);
    }

}
