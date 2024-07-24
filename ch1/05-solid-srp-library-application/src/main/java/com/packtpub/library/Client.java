package com.packtpub.library;

import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Book title: ");
        String title = scanner.nextLine();
        System.out.print("Book author: ");
        String author = scanner.nextLine();
        scanner.close();

        BookPersistence bookPersistence = new BookPersistence();
        Book book = new Book(title, author);
        bookPersistence.save(book);
    }

}