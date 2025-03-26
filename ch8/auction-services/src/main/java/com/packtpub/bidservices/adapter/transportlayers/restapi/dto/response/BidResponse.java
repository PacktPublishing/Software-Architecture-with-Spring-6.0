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
public class BidResponse {
    private String id;
    private String auctionId;
    private Long userId;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}