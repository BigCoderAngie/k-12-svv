package com.example.E_learning.API.Models;

import jakarta.persistence.*;
@Embeddable
public class UserT {

    private String username;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "is_banned")
    private Boolean isBanned;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Boolean getBanned() {
        return isBanned;
    }

    public void setBanned(Boolean banned) {
        isBanned = banned;
    }

    public void setIsBanned(boolean b) {
    }


    // Getters and Setters
}

