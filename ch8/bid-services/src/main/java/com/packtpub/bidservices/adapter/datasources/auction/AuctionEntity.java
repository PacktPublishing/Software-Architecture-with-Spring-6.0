package com.packtpub.bidservices.adapter.datasources.auction;

import com.packtpub.bidservices.adapter.datasources.bid.BidEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "auction")
public class AuctionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "min_bid", nullable = false)
    private BigDecimal minBid;

    @Column(name = "max_bid", nullable = false)
    private BigDecimal maxBid;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BidEntity> bids;

}
