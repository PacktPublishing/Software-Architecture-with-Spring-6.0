package com.packtpub.library;

public class Magazine implements ValidateIssn, Publication {
    private String title;
    private String publisher;
    private String issn;

    // Constructor, getters, and setters

    @Override
    public boolean validateIssn(String issn) {
        // Implement ISSN validation logic
        return issn != null && issn.matches("[0-9]{4}-[0-9]{3}[0-9X]");
    }

    @Override
    public void displayInfo() {
        System.out.println("Magazine Title: " + title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }
}
