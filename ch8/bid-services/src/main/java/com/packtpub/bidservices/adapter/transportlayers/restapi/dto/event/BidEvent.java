package com.packtpub.bidservices.adapter.transportlayers.restapi.dto.event;

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
public class BidEvent {
    private Long id;
    private Long auctionId;
    private Long userId;
    private BigDecimal amount;
    private String status;
    private LocalDateTime createdAt;
}