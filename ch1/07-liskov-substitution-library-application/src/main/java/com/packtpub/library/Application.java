package com.packtpub.library;

public class Application {

    public static void main(String[] args) {
        Book book = new Book("Java", "Jonny X");
        EBook eBook = new EBook("Java", "Jonny X", "http://www.packtpub.com");

        displayBookDetails(book);
        displayBookDetails(eBook);
    }

    public static void displayBookDetails(Book book) {
        if (book instanceof EBook) {
            System.out.print(String.format("Book - Title: %s ; Author: %s ; URL: %s ", book.getTitle(), book.getAuthor(), ((EBook) book).getDownloadUrl()));
            return;
        }
        System.out.println(String.format("Book - Title: %s ; Author: %s", book.getTitle(), book.getAuthor()));
    }

}
