package com.packtpub.library;

public class Book implements ValidateIsbn, Publication {
    private String title;
    private String author;
    private String isbn;

    @Override
    public boolean validateIsbn(String isbn) {
        return isbn != null && isbn.matches("[0-9]{10}");
    }

    @Override
    public void displayInfo() {
        System.out.println("Book Title: " + title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
