package org.example.auctiondemo.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String productName;
    private int basePrice;
    private int currentPrice;

    public Product() {
    }

    public Product(String productName, int basePrice, int currentPrice) {
        this.productName = productName;
        this.basePrice = basePrice;
        this.currentPrice = currentPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }
}
