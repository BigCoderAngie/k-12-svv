package com.example.E_learning.API.Models;

import jakarta.persistence.*;
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;



    @Column(nullable = false, unique = true)
    private String email;

    @Embedded
    private UserT details;

    private String bio;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    // Getters and Setters
}

