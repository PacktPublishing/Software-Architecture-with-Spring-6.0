package com.packtpub.library;

public class BookPersistence {
    private BookSaveFormat saveFormat;

    public BookPersistence(BookSaveFormat saveFormat) {
        this.saveFormat = saveFormat;
    }

    public void saveBook(Book book) {
        this.saveFormat.save(book);
    }
}