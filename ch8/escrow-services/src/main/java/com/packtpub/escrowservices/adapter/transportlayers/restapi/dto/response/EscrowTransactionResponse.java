package com.packtpub.escrowservices.adapter.transportlayers.restapi.dto.response;

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
public class EscrowTransactionResponse {

    private Long id;

    private Long bidId;

    private Long auctionId;

    private Long userId;

    private BigDecimal amount;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}