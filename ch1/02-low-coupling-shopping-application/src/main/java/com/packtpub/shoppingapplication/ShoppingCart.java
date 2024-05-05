package com.packtpub.shoppingapplication;

public class ShoppingCart {

    private final Payment payment;
    public ShoppingCart(Payment payment){
        this.payment = payment;
    }

    public void checkout(double amount) {
        payment.processPayment(amount);
    }

}
