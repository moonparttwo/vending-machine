package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CandyTest {

    @Test
    public void soundTest() {
        Candy candy = new Candy("B3", "Wonka Bar", 1.50, "Candy");
        Assert.assertEquals("Munch Munch, Yum!", candy.sound());
    }
    }