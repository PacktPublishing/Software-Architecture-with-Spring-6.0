package com.packtpub.library;

// Example implementation of BookSaveFormat for saving books to DB
public class DbSaveFormat implements BookSaveFormat {
    @Override
    public void save(Book book) {
        System.out.println("Saving book '" + book.getTitle() + "' to DB");
    }
}