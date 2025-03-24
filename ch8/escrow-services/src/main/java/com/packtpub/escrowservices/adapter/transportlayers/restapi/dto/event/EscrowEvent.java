package com.packtpub.escrowservices.adapter.transportlayers.restapi.dto.event;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EscrowEvent {
    private Long id;
    private Long auctionId;
    private Long userId;
    private BigDecimal amount;
    private String status;
    private LocalDateTime createdAt;
}