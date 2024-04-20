package org.example.auctiondemo.service;

import org.example.auctiondemo.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class AuctionService {
    final List<Auction> auctions = new ArrayList<>();
    final Map<Auction,HandleAuctionOffer> auctionHandlers = new HashMap<>();
    ExecutorService executor = Executors.newCachedThreadPool();


    public List<Auction> getAuctions() {
        return auctions;
    }

    public AuctionService() {
    }

    public Auction findById(String auctionId) {
        return auctions.stream().filter(e-> e.getId().equals(Long.valueOf(auctionId))).findFirst().orElse(null);
    }


    public void makeOfferToAuction(OfferRequest offerRequest) {
        addToAuctionUsers(offerRequest);
        Objects.requireNonNull
                (auctionHandlers
                        .entrySet()
                        .stream()
                        .filter(auctionHandleAuctionOfferEntry -> auctionHandleAuctionOfferEntry.getKey().getId().equals(offerRequest.getAuctionId())).findFirst().orElse(null))
                .getValue()
                .makeOffer(offerRequest.getOffer());
    }

    private void addToAuctionUsers(OfferRequest offerRequest) {
        boolean exists = auctions.stream().anyMatch(e-> e.getUsers().stream().anyMatch(x->x.getId().equals(offerRequest.getOffer().getUser().getId())) && e.getId().equals(offerRequest.getAuctionId()));
        if(!exists) Objects.requireNonNull(auctions.stream().filter(e -> e.getId().equals(offerRequest.getAuctionId())).findFirst().orElse(null)).getUsers().add(offerRequest.getOffer().getUser());
    }

    public void createNewAuction(AuctionRequest auctionRequest) {
        Auction auction = new Auction(auctionRequest.getId(),auctionRequest.getProduct(),auctionRequest.getUser(), LocalDateTime.now());
        auctions.add(auction);
        HandleAuctionOffer auctionOffer = new HandleAuctionOffer(auction);
        executor.execute(auctionOffer);
        auctionHandlers.put(auction,auctionOffer);
    }

    public void terminateAuction(User user, String auctionId) {
        Auction auction = auctions.stream().filter(e -> e.getId().equals(auctionId)).findFirst().orElse(null);
        Objects.requireNonNull(auction).setLastModifiedTime(LocalDateTime.now());
        Objects.requireNonNull(auction).getLogRecords().add(new LogRecord(user,auction.getLastModifiedTime(),"ADMIN FINISHED AUCTION"));
        auction.setEnd(true);
    }
}
