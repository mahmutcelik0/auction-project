package org.example.auctiondemo.service;

import org.example.auctiondemo.model.Auction;
import org.example.auctiondemo.model.LogRecord;
import org.example.auctiondemo.model.Offer;
import org.example.auctiondemo.model.User;

import java.time.LocalDateTime;
import java.util.Objects;

public class HandleAuctionOffer implements Runnable{
    private Auction auction;

    public HandleAuctionOffer(Auction auction) {
        this.auction = auction;
    }

    @Override
    public void run() {

    }
    public void processOffers() throws RuntimeException, InterruptedException {
        while (!auction.getOffers().isEmpty()){
            try {
                Offer offer = auction.getOffers().take();
                if(!auction.isEnd()){
                    if(Objects.requireNonNull(offer).getOfferPrice() > auction.getProduct().getCurrentPrice() && auctionContainsUser(offer.getUser())){
                        auction.getProduct().setCurrentPrice(offer.getOfferPrice());
                        auction.setLastModifiedTime(LocalDateTime.now());
                        auction.getLogRecords().add(new LogRecord(offer.getUser(),auction.getLastModifiedTime(),String.format("{}-{} UPDATED PRICE TO: {}",offer.getUser().getId(),offer.getUser().getUsername(),offer.getOfferPrice())));
                        auction.setWinnerUser(offer.getUser());
                    }
                }else {
                    throw new RuntimeException("AUCTION IS END");
                }
            }catch (RuntimeException | InterruptedException e){
                return;
            }
        }

    }

    private boolean auctionContainsUser(User user) {
        return auction.getUsers().stream().anyMatch(e->{
            return e.getId().equals(user.getId()) && e.getUsername().equals(user.getUsername());
        });
    }


    public void makeOffer(Offer offer){
        try {
            auction.getOffers().put(offer);
            processOffers();
            System.out.println("New offer added");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
