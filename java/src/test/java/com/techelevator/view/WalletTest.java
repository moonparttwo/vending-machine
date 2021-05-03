package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class WalletTest {

    @Test
    public void addMoney() {
        Wallet largeWallet = new Wallet();
        double oneDollar = 1.00;
        largeWallet.addMoney(oneDollar);
        double expected = 1.00;
        Assert.assertEquals(expected, largeWallet.getBalance(), .00001);

    }

    @Test
    public void purchases() {
        Wallet smallWallet = new Wallet();
        double twoDollarBill = 2.00;
        smallWallet.addMoney(twoDollarBill);
        smallWallet.purchases(twoDollarBill);
        double expected1 = 0.0;
        Assert.assertEquals(expected1, smallWallet.getBalance(), .00001);
    }

/*    @Test
    public void giveChange() {
        Wallet wallet1 = new Wallet();
        wallet1.addMoney(.40);
        double expected2 = 1.00;
        wallet1.giveChange(expected2);
        double actual2 = wallet1.giveChange();
    }*/
}