package com.packtpub.bidservices.adapter.datasources.bid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "bid")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidDocument {

    @Id
    private String id;
    private String auctionId;
    private Long userId;
    private BigDecimal amount;
    private LocalDateTime createdAt;

}
