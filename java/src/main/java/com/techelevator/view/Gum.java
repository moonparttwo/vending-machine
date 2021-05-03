package com.techelevator.view;

public class Gum extends Item {
    public Gum(String itemNumber, String itemName, double price, String itemType) {
        super(itemNumber, itemName, price, itemType);
    }

    private String gumSound = "Chew Chew, Yum!";

    @Override
  public String sound() {

        return gumSound;
    }

}