package com.example.E_learning.API.Repository;

import com.example.E_learning.API.Models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepo extends JpaRepository<Enrollment, Long> {
    boolean existsByStudent_StudentIdAndCourse_CourseId(Long studentId, Long courseId);
    List<Enrollment> findByStudent_StudentId(Long studentId);

}
