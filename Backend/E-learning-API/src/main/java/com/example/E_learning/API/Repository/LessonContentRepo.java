package com.example.E_learning.API.Repository;

import com.example.E_learning.API.Models.LessonContent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LessonContentRepo extends JpaRepository<LessonContent, Long> {
    List<LessonContent> findByLesson_LessonIdOrderByDisplayOrderAsc(Long lessonId);
}

