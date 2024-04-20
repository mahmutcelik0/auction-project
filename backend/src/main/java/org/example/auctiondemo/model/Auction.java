package org.example.auctiondemo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Auction {
    private String id;
    private Product product;
    private User createdByUser;
    private LocalDateTime lastModifiedTime;
    private boolean isEnd;
    private User winnerUser;
    private final List<LogRecord> logRecords = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final ArrayBlockingQueue<Offer> offers =new ArrayBlockingQueue<Offer>(100);

    public Auction(String id,Product product, User createdByUser, LocalDateTime lastModifiedTime) {
        this.id = id;
        this.product = product;
        this.createdByUser = createdByUser;
        this.lastModifiedTime = lastModifiedTime;
        this.isEnd = false;
        this.winnerUser = null;
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

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public User getWinnerUser() {
        return winnerUser;
    }

    public void setWinnerUser(User winnerUser) {
        this.winnerUser = winnerUser;
    }

    public List<LogRecord> getLogRecords() {
        return logRecords;
    }

    public List<User> getUsers() {
        return users;
    }

    public ArrayBlockingQueue<Offer> getOffers() {
        return offers;
    }
}


/*
public class Auction {
    private Product product;
    private User createdByUser;
    private LocalDateTime lastModifiedTime;
    private boolean isEnd;
    private User winnerUser;
    private final List<LogRecord> logRecords = new ArrayList<>();
    private final List<User> users = new ArrayList<>();



* */