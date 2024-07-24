package com.packtpub.library;

public class TextFileSaveFormat implements BookSaveFormat {
    public void save(Book book) {
        System.out.println("Saving book '" + book.getTitle() + "' to file.");
    }
}