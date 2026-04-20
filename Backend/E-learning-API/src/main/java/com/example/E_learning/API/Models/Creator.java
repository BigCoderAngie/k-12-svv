package com.example.E_learning.API.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "creator")
public class Creator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "creator_id")
    private Long creatorId;

    @Column(nullable = false, unique = true)
    private String email;

    @Embedded
    private UserT details;

    private String bio;

    // Getters and Setters
    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserT getDetails() {
        return details;
    }

    public void setDetails(UserT details) {
        this.details = details;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
