package org.example.auctiondemo.model;

import java.io.Serializable;

public class AuctionRequest implements Serializable {
    private String id;
    private Product product;
    private User user;

    public AuctionRequest() {
    }

    public AuctionRequest(String id,Product product, User user) {
        this.id = id;
        this.product = product;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
