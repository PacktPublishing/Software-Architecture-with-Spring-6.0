package com.packtpub.onlineauction.etlbatchprocess.bids;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Bid {

    private String originalId;
    private String auctionId;
    private Long userId;
    private BigDecimal amount;
    private LocalDateTime createdAt;

}
