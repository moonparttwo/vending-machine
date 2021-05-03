package com.techelevator.view;

public class Wallet {
    private double balance;
    private int quarter;
    private int dime;
    private int nickel;

    public Wallet() {  // constructor so outside of class can create instance
        this.balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    // a method to add up user money - running balance
    public void addMoney(double totalMoney){
        balance += totalMoney;
    }

    // subtracting balance from totalMoney and returning the balance the user will have after
    public void purchases(double totalMoney) {
        balance -= totalMoney;
    }

    // method to return change in quantities for quarters, dimes, and nickels -DONE
    public void giveChange(Double balance) {
        balance = (balance * 100); // convert to whole number
        quarter = (int) (balance / 25); // start with largest coin amount, quarters - balance/25 returns total amount of quarters -- (int) casts to integer
        balance = balance - (quarter * 25); // subtract quarters from balance to return dimes next
        dime = (int) (balance / 10); // balance/10 returns total amount of dimes
        balance = balance - (dime * 10); // subtract dimes from balance to return nickels last
        nickel = (int) (balance / 5); // balance/5 returns amount of nickels

        System.out.println(
                "Your change: " + quarter + " quarters, " + dime + " dimes, and " + nickel + " nickels.");
    }
}