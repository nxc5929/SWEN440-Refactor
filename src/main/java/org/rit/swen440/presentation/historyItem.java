package org.rit.swen440.presentation;

import java.math.BigDecimal;

public class historyItem
{
    int sku;
    String name;
    BigDecimal price;
    int quantity;

    String printString;

    public historyItem(String name, BigDecimal price, int quantity, int sku)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sku = sku;
        createString();
    }

    public void createString() {
        BigDecimal itemQuantity = new BigDecimal(quantity);
        printString = "Name: " + name + ", Quantity Purchased: " + quantity +
                ", Total Cost: " + price.multiply(itemQuantity) + ", SKU Code: " + sku;
    }

    public String getPrintString() {
        return printString;
    }
}
