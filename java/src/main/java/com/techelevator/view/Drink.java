package com.techelevator.view;

public class Drink extends Item {
    public Drink(String itemNumber, String itemName, double price, String itemType) {
        super(itemNumber, itemName, price, itemType);
    }
    private String drinkSound = "Glug Glug, Yum!";
    @Override
    public String sound() {
        return drinkSound;
    }

}
