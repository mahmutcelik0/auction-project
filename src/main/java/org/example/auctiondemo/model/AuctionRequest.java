package org.example.auctiondemo.model;

import java.io.Serializable;

public class AuctionRequest implements Serializable {
    private Product product;
    private User user;

    public AuctionRequest() {
    }

    public AuctionRequest(Product product, User user) {
        this.product = product;
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
