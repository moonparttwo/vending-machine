package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GumTest {

    @Test
    public void sound() {
        Gum gum = new Gum("D3", "Chiclets", 0.75, "Gum");
        Assert.assertEquals("Chew Chew, Yum!", gum.sound());
    }
}