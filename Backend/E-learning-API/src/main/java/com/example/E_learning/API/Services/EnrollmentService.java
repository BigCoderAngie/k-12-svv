package com.example.E_learning.API.Services;

import com.example.E_learning.API.Models.Course;
import com.example.E_learning.API.Models.Enrollment;
import com.example.E_learning.API.Models.Student;
import com.example.E_learning.API.Repository.CourseRepo;
import com.example.E_learning.API.Repository.EnrollmentRepo;
import com.example.E_learning.API.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepo enrollmentRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Transactional
    public Enrollment enrollStudent(Long studentId, Long courseId) {
        if (enrollmentRepo.existsByStudent_StudentIdAndCourse_CourseId(studentId, courseId)) {
            throw new IllegalStateException("Student already enrolled in this course");
        }

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(LocalDateTime.now());

        return enrollmentRepo.save(enrollment);
    }

    public List<Course> getCoursesByStudent(Long studentId) {
        List<Enrollment> enrollments = enrollmentRepo.findByStudent_StudentId(studentId);
        return enrollments.stream()
                .map(Enrollment::getCourse)
                .collect(Collectors.toList());
    }

}
