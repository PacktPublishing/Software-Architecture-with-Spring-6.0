package com.packtpub.bidservices.internal.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Bid {
    private Long id;
    private Long auctionId;
    private Long userId;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
