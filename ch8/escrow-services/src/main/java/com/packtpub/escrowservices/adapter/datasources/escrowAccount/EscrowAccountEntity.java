package com.packtpub.escrowservices.adapter.datasources.escrowAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "escrow_account")
public class EscrowAccountEntity {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "available_funds", nullable = false)
    private BigDecimal availableFunds;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}