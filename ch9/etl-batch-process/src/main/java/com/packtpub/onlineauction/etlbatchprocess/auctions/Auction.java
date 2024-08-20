package com.packtpub.onlineauction.etlbatchprocess.auctions;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Auction {

    private String originalId;
    private String description;
    private BigDecimal maxbid;
    private BigDecimal minbid;
    private Long productid;
    private boolean isActive;
    private LocalDateTime createdAt;

}
