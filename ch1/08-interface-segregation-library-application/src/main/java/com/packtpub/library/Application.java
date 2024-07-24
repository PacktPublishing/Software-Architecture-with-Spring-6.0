package com.packtpub.library;

public class Application {

    public static void main(String[] args) {
        Book book = new Book();
        book.setAuthor("Unknown Author");
        book.setTitle("Any Book");
        book.setIsbn("978-2-12-345680-3");

        System.out.println(book.validateIsbn(book.getIsbn()));

        Magazine magazine = new Magazine();

        magazine.setPublisher("Unknown Publisher");
        magazine.setTitle("Any Magazine");
        magazine.validateIssn("1050-124X");

        System.out.println(magazine.validateIssn(magazine.getIssn()));

    }


}
