package com.packtpub.escrowservices.internal.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class EscrowAccount {

    private String id;
    private String description;
    private Long productId;
    private BigDecimal minBid;
    private BigDecimal maxBid;
    private boolean active;
    private LocalDateTime createdAt;

}
