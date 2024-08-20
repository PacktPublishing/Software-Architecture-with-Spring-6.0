package com.packtpub.onlineauction.etlbatchprocess.auctions;

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
public class AuctionDto {
    private String originalId;
    private String description;
    private BigDecimal maxbid;
    private BigDecimal minbid;
    private Long productid;
    private boolean isActive;
    private LocalDateTime createdAt;

}
