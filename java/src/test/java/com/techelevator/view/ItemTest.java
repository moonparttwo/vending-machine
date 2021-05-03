package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest extends Item {

    public ItemTest(String itemNumber, String itemName, double price, String itemType) {
        super(itemNumber, itemName, price, itemType);
    }

    @Test
    public void isOutOfStockTest() {

        ItemTest testObject = new ItemTest("D4", "Triplemint", 0.75, "Gum");
        testObject.setQuantity(0);
        boolean expected = true;
        boolean actual = testObject.isOutOfStock();
        Assert.assertEquals(expected, actual);

        testObject.setQuantity(2);
        boolean expected1 = false;
        boolean actual1 = !testObject.isOutOfStock();
        Assert.assertEquals(expected1, actual1);
    }
}