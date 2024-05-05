package com.packtpub.shoppingapplication;

public class DebitCardPayment implements Payment{
    @Override
    public void processPayment(double amount) {
        System.out.printf("Payment by debit card - value $%.2f%n", amount);
    }
}
