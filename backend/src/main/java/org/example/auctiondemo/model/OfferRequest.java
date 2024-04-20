package org.example.auctiondemo.model;

public class OfferRequest {
    private Offer offer;
    private String auctionId;

    public OfferRequest(Offer offer, String auctionId) {
        this.offer = offer;
        this.auctionId = auctionId;
    }

    public OfferRequest() {
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }
}
