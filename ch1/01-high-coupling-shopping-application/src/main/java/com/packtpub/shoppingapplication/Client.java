package com.packtpub.shoppingapplication;

import java.util.Scanner;

public class Client {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Amount : ");
        String amount = scanner.nextLine().replace(",", ".");
        System.out.print("Credit or Debit? Enter \"CC\" for credit, or any other value for debit. : ");
        String typePayment = scanner.nextLine();
        scanner.close();

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.checkout(typePayment, Double.parseDouble(amount));

    }

}