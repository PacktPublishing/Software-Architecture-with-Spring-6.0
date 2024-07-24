package com.packtpub.shoppingapplication;

public class CreditCardPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.printf("Payment by credit card - value $%.2f%n", amount);
    }
}
