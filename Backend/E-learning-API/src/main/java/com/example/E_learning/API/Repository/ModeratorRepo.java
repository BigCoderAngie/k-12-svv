package com.example.E_learning.API.Repository;

import com.example.E_learning.API.Models.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModeratorRepo extends JpaRepository<Moderator, Long> {
    Optional<Moderator> findByEmail(String email);
}
