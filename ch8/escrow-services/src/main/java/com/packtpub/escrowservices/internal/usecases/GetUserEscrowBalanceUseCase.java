package com.packtpub.escrowservices.internal.usecases;

import com.packtpub.escrowservices.adapter.datasources.escrowAccount.EscrowAccountEntity;
import com.packtpub.escrowservices.internal.entity.EscrowTransaction;
import com.packtpub.escrowservices.internal.repositories.EscrowAccountRepository;
import com.packtpub.escrowservices.internal.repositories.EscrowTransactionRepository;

import java.math.BigDecimal;

public class GetUserEscrowBalanceUseCase {

    private final EscrowAccountRepository escrowAccountRepository;

    public GetUserEscrowBalanceUseCase(EscrowAccountRepository escrowAccountRepository) {
        this.escrowAccountRepository = escrowAccountRepository;
    }

    public BigDecimal execute(Long userId) {
        return escrowAccountRepository.findById(userId);
    }

}