package com.packtpub.escrowservices.internal.repositories;

import com.packtpub.escrowservices.internal.entity.EscrowTransaction;

import java.math.BigDecimal;

public interface EscrowTransactionRepository {
    EscrowTransaction save(EscrowTransaction escrowTransaction);
    BigDecimal findTotalReservationsById(Long userId);
}

