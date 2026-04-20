package com.example.E_learning.API.Services;

import com.example.E_learning.API.Models.Course;
import com.example.E_learning.API.Models.Lesson;
import com.example.E_learning.API.Repository.CourseRepo;
import com.example.E_learning.API.Repository.LessonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {

    @Autowired
    private LessonRepo lessonRepo;

    @Autowired
    private CourseRepo courseRepo;

    public Lesson createLesson(Long courseId, String title, String description) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        Lesson lesson = new Lesson();
        lesson.setCourse(course);
        lesson.setTitle(title);
        lesson.setDescription(description);
        lesson.setIsFlagged(false);

        return lessonRepo.save(lesson);
    }

    public Optional<Lesson> getLessonById(Long lessonId) {
        return lessonRepo.findById(lessonId);
    }

    public List<Lesson> getLessonsByCourse(Long courseId) {
        return lessonRepo.findByCourse_CourseIdOrderByLessonIdAsc(courseId);
    }


}
