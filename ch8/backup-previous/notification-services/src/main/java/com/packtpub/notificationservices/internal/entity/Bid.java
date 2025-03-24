package com.packtpub.notificationservices.internal.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Bid {
    private String id;
    private String auctionId;
    private Long userId;
    private BigDecimal amount;
    private LocalDateTime createdAt;

    public Bid() {
    }

    public Bid(String id, String auctionId, Long userId, BigDecimal amount, LocalDateTime createdAt) {
        this.id = id;
        this.auctionId = auctionId;
        this.userId = userId;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id='" + id + '\'' +
                ", auctionId='" + auctionId + '\'' +
                ", userId=" + userId +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                '}';
    }
}
