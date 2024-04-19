package org.example.auctiondemo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Auction implements Serializable {
    private Long id;
    private Product product;
    private User createdByUser;
    private LocalDateTime lastModifiedTime;
    private boolean isEnd;
    private User winnerUser;
    private final List<LogRecord> logRecords = new ArrayList<>();
    private final List<User> users = new ArrayList<>();

    public Auction(Product product, User createdByUser, LocalDateTime lastModifiedTime) {
        this.id = new Random().nextLong(0,10000);
        this.product = product;
        this.createdByUser = createdByUser;
        this.lastModifiedTime = lastModifiedTime;
        this.isEnd = false;
        this.winnerUser = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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