package org.example.auctiondemo.model;

public class Offer {
    private User user;
    private int offerPrice;

    public Offer(User user, int offerPrice) {
        this.user = user;
        this.offerPrice = offerPrice;
    }

    public Offer() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(int offerPrice) {
        this.offerPrice = offerPrice;
    }
}
