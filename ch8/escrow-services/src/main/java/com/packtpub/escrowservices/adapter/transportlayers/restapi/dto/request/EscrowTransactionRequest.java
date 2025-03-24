package com.packtpub.escrowservices.adapter.transportlayers.restapi.dto.request;

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
public class EscrowTransactionRequest {
    private Long id;
    private Long auctionId;
    private Long userId;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}