package com.example.E_learning.API.Repository;

import com.example.E_learning.API.Models.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreatorRepo extends JpaRepository<Creator, Long> {
    Optional<Creator> findByEmail(String email);
}
