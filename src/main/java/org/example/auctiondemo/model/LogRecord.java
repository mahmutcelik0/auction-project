package org.example.auctiondemo.model;

import java.time.LocalDateTime;

public class LogRecord {
    private User user;
    private LocalDateTime time;

    public LogRecord(User user, LocalDateTime time) {
        this.user = user;
        this.time = time;
    }

    public LogRecord() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
