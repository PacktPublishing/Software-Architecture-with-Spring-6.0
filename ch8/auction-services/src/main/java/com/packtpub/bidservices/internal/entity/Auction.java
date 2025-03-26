package com.packtpub.bidservices.internal.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Auction {

    private Long id;
    private String description;
    private Long productId;
    private BigDecimal minBid;
    private BigDecimal maxBid;
    private boolean active;
    private LocalDateTime createdAt;

}
