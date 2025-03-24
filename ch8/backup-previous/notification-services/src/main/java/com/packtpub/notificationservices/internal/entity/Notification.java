package com.packtpub.notificationservices.internal.entity;

import java.util.List;

public class Notification {
    private String id;
    private String auction;
    private List<String> emails;

    public Notification() {
    }

    public Notification(String id, String auction, List<String> emails) {
        this.id = id;
        this.auction = auction;
        this.emails = emails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuction() {
        return auction;
    }

    public void setAuction(String auction) {
        this.auction = auction;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
}
