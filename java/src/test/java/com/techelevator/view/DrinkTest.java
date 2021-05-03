package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DrinkTest {

    @Test
    public void sound() {
        Drink drink = new Drink("C1", "Cola", 1.25, "Drink");
        Assert.assertEquals("Glug Glug, Yum!", drink.sound());
    }
}