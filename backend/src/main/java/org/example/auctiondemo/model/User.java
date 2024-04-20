package org.example.auctiondemo.model;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String username;
    private Role role;

    public User(String  id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
