package com.example.E_learning.API.Services;

import com.example.E_learning.API.DTO.LoginRequest;
import com.example.E_learning.API.DTO.UserAuthData;
import com.example.E_learning.API.Models.Moderator;
import com.example.E_learning.API.Models.Student;
import com.example.E_learning.API.Models.UserT;
import com.example.E_learning.API.Repository.CreatorRepo;
import com.example.E_learning.API.Repository.ModeratorRepo;
import com.example.E_learning.API.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import com.example.E_learning.API.Models.Creator;
import com.example.E_learning.API.DTO.SignupRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CreatorRepo creatorRepo;

    @Autowired
    private ModeratorRepo moderatorRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Object signup(SignupRequest request) {
        String role = request.getRole().toUpperCase();
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        UserT userDetails = new UserT();
        userDetails.setUsername(request.getUsername());
        userDetails.setPasswordHash(hashedPassword);
        userDetails.setIsBanned(false);

        return switch (role) {
            case "STUDENT" -> {
                Student student = new Student();
                student.setEmail(request.getEmail());
                student.setDetails(userDetails);
                student.setBio(request.getBio());
                yield studentRepo.save(student);
            }
            case "CREATOR" -> {
                Creator creator = new Creator();
                creator.setEmail(request.getEmail());
                creator.setDetails(userDetails);
                creator.setBio(request.getBio());
                yield creatorRepo.save(creator);
            }
            case "MODERATOR" -> {
                Moderator moderator = new Moderator();
                moderator.setEmail(request.getEmail());
                moderator.setDetails(userDetails);
                yield moderatorRepo.save(moderator);
            }
            default -> throw new IllegalArgumentException("Invalid role selected");
        };
    }

    public Optional<UserAuthData> authenticateUser(LoginRequest loginRequest) {
        Optional<Student> studentOpt = studentRepo.findByEmail(loginRequest.getEmail());
        if (studentOpt.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), studentOpt.get().getDetails().getPasswordHash())) {
            Student student = studentOpt.get();
            return Optional.of(new UserAuthData(student.getStudentId(), studentOpt.get().getEmail(), "STUDENT"));
        }

        Optional<Creator> creatorOpt = creatorRepo.findByEmail(loginRequest.getEmail());
        if (creatorOpt.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), creatorOpt.get().getDetails().getPasswordHash())) {
            Creator creator = creatorOpt.get();
            return Optional.of(new UserAuthData(creator.getCreatorId(), creator.getEmail(), "CREATOR"));
        }

        Optional<Moderator> moderatorOpt = moderatorRepo.findByEmail(loginRequest.getEmail());
        if (moderatorOpt.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), moderatorOpt.get().getDetails().getPasswordHash())) {
            Moderator moderator = moderatorOpt.get();
            return Optional.of(new UserAuthData(moderator.getModeratorId(), moderator.getEmail(), "MODERATOR"));
        }

        return Optional.empty();
    }

}
