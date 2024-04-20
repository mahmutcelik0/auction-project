package org.example.auctiondemo.service;

import org.example.auctiondemo.model.Auction;
import org.example.auctiondemo.model.LogRecord;
import org.example.auctiondemo.model.Offer;
import org.example.auctiondemo.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AuctionService {
    final List<Auction> auctions = new ArrayList<>();

    public List<Auction> getAuctions() {
        return auctions;
    }

    public AuctionService() {
    }

    public Auction findById(String auctionId) {
        return auctions.stream().filter(e-> e.getId().equals(Long.valueOf(auctionId))).findFirst().orElse(null);
    }


}
