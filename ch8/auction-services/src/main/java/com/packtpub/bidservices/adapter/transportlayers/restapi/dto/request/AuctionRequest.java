package com.packtpub.bidservices.adapter.transportlayers.restapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuctionRequest {
    private String description;
    private Long productId;
    private BigDecimal minBid;
    private BigDecimal maxBid;
    private boolean active;
    private LocalDateTime createdAt = LocalDateTime.now();
}