package com.packtpub.bidservices.adapter.transportlayers.restapi.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EscrowEvent {
    private Long id;
    private Long auctionId;
    private Long userId;
    private String amount;
    private String status;
    private LocalDateTime createdAt;
}