package com.packtpub.escrowservices.adapter.datasources.escrowTransaction;

import com.packtpub.escrowservices.adapter.datasources.escrowAccount.EscrowAccountEntity;
import com.packtpub.escrowservices.adapter.transportlayers.restapi.EscrowTransactionMapper;
import com.packtpub.escrowservices.internal.entity.EscrowTransaction;
import com.packtpub.escrowservices.internal.repositories.EscrowTransactionRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class EscrowTransactionDatasource implements EscrowTransactionRepository {

    private final EscrowTransactionJpaRepository escrowTransactionJpaRepository;

    public EscrowTransactionDatasource(EscrowTransactionJpaRepository escrowTransactionJpaRepository) {
        this.escrowTransactionJpaRepository = escrowTransactionJpaRepository;
    }

    @Override
    public EscrowTransaction save(EscrowTransaction escrowTransaction) {
        EscrowTransactionEntity escrowTransactionEntity = EscrowTransactionMapper.INSTANCE.toEntity(escrowTransaction);
        return EscrowTransactionMapper.INSTANCE.map(escrowTransactionJpaRepository.save(escrowTransactionEntity));
    }

    @Override
    public BigDecimal findTotalReservationsById(Long userId) {
        return escrowTransactionJpaRepository.findTotalReservationsById(userId);
    }

}
