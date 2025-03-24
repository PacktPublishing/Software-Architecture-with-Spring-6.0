package com.packtpub.escrowservices.internal.usecases;

import com.packtpub.escrowservices.internal.entity.EscrowAccount;
import com.packtpub.escrowservices.internal.repositories.EscrowAccountRepository;

public class CreateEscrowAccountUseCase {
    private final EscrowAccountRepository escrowAccountRepository;

    public CreateEscrowAccountUseCase(EscrowAccountRepository escrowAccountRepository) {
        this.escrowAccountRepository = escrowAccountRepository;
    }


    public EscrowAccount execute(EscrowAccount escrowAccount) {
        return escrowAccountRepository.save(escrowAccount);
    }

}