package com.techelevator.view;

public class Chips extends Item { // need to figure out how to add multiple chips?

    public Chips(String itemNumber, String itemName, double price, String itemType) {
        super(itemNumber, itemName, price, itemType);
    }

    private String chipsSound = "Crunch Crunch, Yum!";
    @Override
   public String sound() {
        return chipsSound;
    }
// make sound from abstract method -- returns the string of sound

}
