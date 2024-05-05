package com.packtpub.usermanagement;

import java.util.Scanner;

public class Client {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter A to add user; ");
        System.out.print("U to update user; ");
        System.out.print("V to validate email; ");
        System.out.print("Enter S to send email : ");
        execute(scanner.nextLine());
        scanner.close();

    }

    private static void execute(String input) {
         switch (input) {
            case "A" -> new UserManager().addUser(new User(1L, "Super User", "superuser@email.com"));
            case "U" -> new UserManager().updateUser(new User(1L, "Super User", "superuser@email.com"));
            case "V" -> new UserManager().validateEmail("superuser@email.com");
            case "S" -> new UserManager().sendEmail();
            default -> System.out.println("Invalid input");
        };
    }

}