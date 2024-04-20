package org.example.auctiondemo.service;

import org.example.auctiondemo.model.Auction;
import org.example.auctiondemo.model.LogRecord;
import org.example.auctiondemo.model.Offer;
import org.example.auctiondemo.model.User;

import java.time.LocalDateTime;
import java.util.Objects;

public class HandleAuctionOffer implements Runnable{
    private Auction auction;

    @Override
    public void run() {
        while (!auction.isEnd()){
            while (!auction.getOffers().isEmpty()){
                try {
                    processOffers();
                }catch (RuntimeException | InterruptedException e){
                    return;
                }
            }
        }
    }
    public void processOffers() throws RuntimeException, InterruptedException {
        Offer offer = auction.getOffers().take();
        if(!auction.isEnd()){
            if(Objects.requireNonNull(offer).getOfferPrice() > auction.getProduct().getCurrentPrice() && auctionContainsUser(offer.getUser())){
                auction.getProduct().setCurrentPrice(offer.getOfferPrice());
                auction.setLastModifiedTime(LocalDateTime.now());
                auction.getLogRecords().add(new LogRecord(offer.getUser(),auction.getLastModifiedTime()));
            }
        }else {
            throw new RuntimeException("AUCTION IS END");
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
            System.out.println("New offer added");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
