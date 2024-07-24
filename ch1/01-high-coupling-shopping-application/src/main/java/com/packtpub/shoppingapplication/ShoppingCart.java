package com.packtpub.shoppingapplication;

public class ShoppingCart {

    private CreditCardPayment cc = new CreditCardPayment();
    private DebitCardPayment debit = new DebitCardPayment();

    public void checkout(String typePayment, double amount) {
        if (typePayment.equals("CC")) {
            cc.processCreditCardPayment(amount);
        } else {
            debit.processDebitCardPayment(amount);
        }
    }

}
