package com.packt.simpletests;

import java.math.BigDecimal;

public class AuctionBid {

    private final String bidder;
    private BigDecimal highestBid;

    public AuctionBid(String bidder) {
        this.bidder = bidder;
        this.highestBid = BigDecimal.ZERO;
    }

    public void placeBid(BigDecimal amount) {
        if (amount.compareTo(highestBid) <= 0) {
            throw new IllegalArgumentException("Bid must be higher than current highest bid");
        }
        this.highestBid = amount;
    }

    public BigDecimal getHighestBid() {
        return highestBid;
    }

    public String getBidder() {
        return bidder;
    }
}