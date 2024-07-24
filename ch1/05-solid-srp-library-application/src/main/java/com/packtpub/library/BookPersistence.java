package com.packtpub.library;

public class BookPersistence {
    public void save(Book book){
        System.out.printf("Book: %s saved.", book.getTitle() + " ; Author : " + book.getAuthor());
    }
}
