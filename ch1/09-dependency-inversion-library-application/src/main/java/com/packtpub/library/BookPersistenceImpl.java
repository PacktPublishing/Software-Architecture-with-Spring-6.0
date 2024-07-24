package com.packtpub.library;

// Implementation of BookPersistence using TextFileSaveFormat
public class BookPersistenceImpl implements BookPersistence {
    private BookSaveFormat saveFormat;

    // Constructor with dependency injection
    public BookPersistenceImpl(BookSaveFormat saveFormat) {
        this.saveFormat = saveFormat;
    }

    // Method to save a book using the specified save format
    @Override
    public void saveBook(Book book) {
        saveFormat.save(book);
    }
}
