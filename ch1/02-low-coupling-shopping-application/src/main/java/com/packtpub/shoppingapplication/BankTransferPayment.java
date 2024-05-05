package com.packtpub.shoppingapplication;

public class BankTransferPayment implements Payment{
    @Override
    public void processPayment(double amount) {
        System.out.printf("Payment by bank transfer - value $%.2f%n", amount);
    }
}
