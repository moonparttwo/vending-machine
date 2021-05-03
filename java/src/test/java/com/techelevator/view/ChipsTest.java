package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChipsTest {

    @Test
    public void soundTest() {
        Chips chips = new Chips("A1", "Potato Crisps", 3.05, "Chip");
        Assert.assertEquals("Crunch Crunch, Yum!", chips.sound());
    }
}