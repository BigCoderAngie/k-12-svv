package com.example.E_learning.API.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "moderator")
public class Moderator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moderator_id")
    private Long moderatorId;

    @Column(nullable = false, unique = true)
    private String email;

    @Embedded
    private UserT details;

    // Getters and Setters
    public Long getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(Long moderatorId) {
        this.moderatorId = moderatorId;
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
}
