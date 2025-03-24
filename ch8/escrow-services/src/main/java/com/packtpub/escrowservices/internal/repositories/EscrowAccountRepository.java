package com.packtpub.escrowservices.internal.repositories;

import com.packtpub.escrowservices.internal.entity.EscrowAccount;

import java.math.BigDecimal;

public interface EscrowAccountRepository {
    EscrowAccount save(EscrowAccount escrowAccount);
    BigDecimal findById(Long userId);
}

