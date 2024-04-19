package org.example.model;

import java.time.LocalDateTime;

public class LogRecord {
    private User user;
    private LocalDateTime time;

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
