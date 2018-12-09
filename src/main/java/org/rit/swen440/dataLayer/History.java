package org.rit.swen440.dataLayer;

import lombok.AccessLevel;
import lombok.Setter;

import java.math.BigDecimal;

public class History {
    @Setter(AccessLevel.PRIVATE)

    private int skuCode;
    private int quantity;
    private String name;
    private BigDecimal price;

    public History(int skuCode, int quantity, String name, BigDecimal price) {
        this.skuCode = skuCode;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    public int getSkuCode() { return skuCode; }

    public void setSkuCode(int skuCode) { this.skuCode = skuCode; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) { this.price = price; }
}
