package com.packtpub.escrowservices.internal.usecases;

import com.packtpub.escrowservices.internal.entity.EscrowTransaction;
import com.packtpub.escrowservices.internal.repositories.EscrowTransactionRepository;

public class CreateEscrowTransationUseCase {
    private final EscrowTransactionRepository escrowTransactionRepository;

    public CreateEscrowTransationUseCase(EscrowTransactionRepository escrowTransactionRepository) {
        this.escrowTransactionRepository = escrowTransactionRepository;
    }

    public EscrowTransaction execute(EscrowTransaction escrowTransaction) {
        return escrowTransactionRepository.save(escrowTransaction);
    }

}