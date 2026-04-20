package com.example.E_learning.API.Repository;

import com.example.E_learning.API.Models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepo extends JpaRepository<Lesson, Long> {
    List<Lesson> findByCourse_CourseIdOrderByLessonIdAsc(Long courseId);
}
