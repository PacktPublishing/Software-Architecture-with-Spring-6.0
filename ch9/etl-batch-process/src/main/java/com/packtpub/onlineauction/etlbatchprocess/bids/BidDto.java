package com.packtpub.onlineauction.etlbatchprocess.bids;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BidDto {
    private String originalId;
    private String auctionId;
    private Long userId;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
