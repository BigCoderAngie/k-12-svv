package com.example.E_learning.API.Controllers;

import com.example.E_learning.API.DTO.LoginRequest;
import com.example.E_learning.API.DTO.SignupRequest;
import com.example.E_learning.API.DTO.UserAuthData;
import com.example.E_learning.API.Models.Creator;
import com.example.E_learning.API.Models.Moderator;
import com.example.E_learning.API.Models.Student;
import com.example.E_learning.API.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
//        try {
//            authService.signup(signupRequest);
//            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }

        Object savedUser = authService.signup(signupRequest);
        if (savedUser instanceof Student student) {
            return ResponseEntity.ok(new UserAuthData(student.getStudentId(), student.getEmail(), "STUDENT"));
        } else if (savedUser instanceof Creator creator) {
            return ResponseEntity.ok(new UserAuthData(creator.getCreatorId(), creator.getEmail(), "CREATOR"));
        } else if (savedUser instanceof Moderator moderator) {
            return ResponseEntity.ok(new UserAuthData(moderator.getModeratorId(), moderator.getEmail(), "MODERATOR"));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected role");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<UserAuthData> authResult = authService.authenticateUser(loginRequest);

        if(authResult.isPresent()){
            return ResponseEntity.ok(authResult.get());
        } else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}
