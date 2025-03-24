package com.packtpub.escrowservices.internal.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EscrowTransaction {
    private Long id;
    private Long auctionId;
    private Long bidId;
    private Long userId;
    private BigDecimal amount;
    private String status;
    private LocalDateTime createdAt;

    public EscrowTransaction(){}

    public EscrowTransaction(Long id, Long auctionId, Long bidId, Long userId, BigDecimal amount, String status, LocalDateTime createdAt) {
        this.id = id;
        this.auctionId = auctionId;
        this.bidId = bidId;
        this.userId = userId;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Long auctionId) {
        this.auctionId = auctionId;
    }

    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
