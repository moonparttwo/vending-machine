package com.techelevator.view;

public class Candy extends Item {
    public Candy(String itemNumber, String itemName, double price, String itemType) {
        super(itemNumber, itemName, price, itemType);
    }
    private String candySound = "Munch Munch, Yum!";
    @Override
    public String sound() {
        return candySound;
    }

}
