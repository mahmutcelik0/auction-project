package org.example.auctiondemo.model;

import java.time.LocalDateTime;

public class LogRecord {
    private User user;
    private LocalDateTime time;
    private String message;

    public LogRecord(User user, LocalDateTime time, String message) {
        this.user = user;
        this.time = time;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
