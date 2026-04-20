package com.example.E_learning.API.Services;

import com.example.E_learning.API.DTO.SignupRequest;
import com.example.E_learning.API.Models.Creator;
import com.example.E_learning.API.Models.Moderator;
import com.example.E_learning.API.Models.Student;
import com.example.E_learning.API.Models.UserT;
import com.example.E_learning.API.Repository.CreatorRepo;
import com.example.E_learning.API.Repository.ModeratorRepo;
import com.example.E_learning.API.Repository.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepository;
    private ModeratorRepo moderatorRepo;
    private CreatorRepo creatorRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional
    public Student createStudent(Student student) {
        // Hash the password before saving
        String hashed = passwordEncoder.encode(student.getDetails().getPasswordHash());
        student.getDetails().setPasswordHash(hashed);

        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
    public Optional<Student> getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Transactional
    public void signup(SignupRequest request) {
        String role = request.getRole().toUpperCase();
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        UserT userDetails = new UserT();
        userDetails.setUsername(request.getUsername());
        userDetails.setPasswordHash(hashedPassword);
        userDetails.setIsBanned(false);

        switch (role) {
            case "STUDENT" -> {
                Student student = new Student();
                student.setEmail(request.getEmail());
                student.setDetails(userDetails);
                student.setBio(request.getBio());
                studentRepository.save(student);
            }
            case "CREATOR" -> {
                Creator creator = new Creator();
                creator.setEmail(request.getEmail());
                creator.setDetails(userDetails);
                creator.setBio(request.getBio());
                creatorRepo.save(creator);
            }
            case "MODERATOR" -> {
                Moderator moderator = new Moderator();
                moderator.setEmail(request.getEmail());
                moderator.setDetails(userDetails);
                moderatorRepo.save(moderator);
            }
            default -> throw new IllegalArgumentException("Invalid role selected");
        }
    }

}

