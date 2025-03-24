package com.packtpub.escrowservices.internal.usecases;

import com.packtpub.escrowservices.internal.repositories.EscrowTransactionRepository;

import java.math.BigDecimal;

public class GetUserEscrowReservationsUseCase {

    private final EscrowTransactionRepository escrowTransactionRepository;

    public GetUserEscrowReservationsUseCase(EscrowTransactionRepository escrowTransactionRepository) {
        this.escrowTransactionRepository = escrowTransactionRepository;
    }

    public BigDecimal execute(Long userId) {
        return escrowTransactionRepository.findTotalReservationsById(userId);
    }

}