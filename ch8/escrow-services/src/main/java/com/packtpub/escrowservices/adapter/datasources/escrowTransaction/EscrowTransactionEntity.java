package com.packtpub.escrowservices.adapter.datasources.escrowTransaction;

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
@Table(name = "escrow_transaction")
public class EscrowTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bid_id", nullable = false, unique = true)
    private Long bidId;

    @Column(name = "auction_id", nullable = false)
    private Long auctionId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}