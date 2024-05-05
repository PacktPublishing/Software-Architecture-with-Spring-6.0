package com.packtpub.shoppingapplication;

import java.util.Scanner;

public class Client {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Amount : ");
        String amount = scanner.nextLine().replace(",", ".");
        System.out.print("Credit, Debit or Bank Transfer? Enter \"CC\" for credit, \"DD\" for debit or any other value for bank transfer. : ");
        String typePayment = scanner.nextLine();
        scanner.close();

        ShoppingCart shoppingCart = new ShoppingCart(getTypeOfPayment(typePayment));
        shoppingCart.checkout(Double.parseDouble(amount));

    }

    private static Payment getTypeOfPayment(String typePayment) {
        return switch (typePayment) {
            case "CC" -> new CreditCardPayment();
            case "DD" -> new DebitCardPayment();
            default -> new BankTransferPayment();
        };
    }

}