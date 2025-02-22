package com.packtpub.bidservices.adapter.datasources.auction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "auction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuctionDocument {

    @Id
    private String id;
    private String description;
    private Long productId;
    private BigDecimal minBid;
    private BigDecimal maxBid;
    private boolean active;
    private LocalDateTime createdAt;

}
