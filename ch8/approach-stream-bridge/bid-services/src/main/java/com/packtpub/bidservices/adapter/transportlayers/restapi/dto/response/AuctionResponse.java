package com.packtpub.bidservices.adapter.transportlayers.restapi.dto.response;

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
public class AuctionResponse {
    private String id;
    private String description;
    private Long productId;
    private BigDecimal minBid;
    private BigDecimal maxBid;
    private boolean active;
    private LocalDateTime createdAt;
}