package com.packtpub.library;

import java.util.Scanner;

public class ClientApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Book title: ");
        String title = scanner.nextLine();
        System.out.print("Book author: ");
        String author = scanner.nextLine();
        System.out.print("Press \"T\" to save in TextFile or any other letter to save in database. ");
        String saveType = scanner.nextLine();
        scanner.close();

        Book book = new Book(title, author);
        BookPersistence bookPersistence = null;
        if (saveType.equals("T")) {
            bookPersistence = new BookPersistence (new TextFileSaveFormat());
        } else {
            bookPersistence = new BookPersistence (new DbSaveFormat());
        }
        bookPersistence.saveBook(book);

    }
}
