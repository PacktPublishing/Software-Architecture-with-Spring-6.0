package com.packtpub.library;

public class BookPersistence {
    public void saveToFile(Book book, String filename){
        System.out.printf("Book: %s saved to %s.", book.getTitle() + " ; Author : " + book.getAuthor(), filename);
    }
}
