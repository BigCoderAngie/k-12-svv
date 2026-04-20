package com.example.E_learning.API.Controllers;

import com.example.E_learning.API.DTO.EnrollRequest;
import com.example.E_learning.API.Models.Course;
import com.example.E_learning.API.Models.Enrollment;
import com.example.E_learning.API.Services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public ResponseEntity<?> enrollStudent(@RequestBody EnrollRequest request) {
        try {
            Enrollment enrollment = enrollmentService.enrollStudent(
                    request.getStudentId(),
                    request.getCourseId()
            );
            return ResponseEntity.ok(enrollment);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Course>> getEnrolledCourses(@PathVariable Long studentId) {
        List<Course> courses = enrollmentService.getCoursesByStudent(studentId);
        return ResponseEntity.ok(courses);
    }

}
