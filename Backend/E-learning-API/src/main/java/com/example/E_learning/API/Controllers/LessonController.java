package com.example.E_learning.API.Controllers;

import com.example.E_learning.API.DTO.CreateLessonRequest;
import com.example.E_learning.API.DTO.LessonResponse;
import com.example.E_learning.API.Models.Lesson;
import com.example.E_learning.API.Services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @PostMapping("/create")
    public ResponseEntity<LessonResponse> createLesson(@RequestBody CreateLessonRequest request) {
        Lesson lesson = lessonService.createLesson(
                request.getCourseId(),
                request.getTitle(),
                request.getDescription()
        );

        LessonResponse lessonResponse = new LessonResponse(
                lesson.getLessonId(),
                lesson.getTitle(),
                lesson.getDescription(),
                lesson.getCourse().getCourseId(),
                lesson.getCourse().getCreator().getCreatorId()
        );

        return ResponseEntity.ok(lessonResponse);
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Long lessonId) {
        return lessonService.getLessonById(lessonId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Lesson>> getLessonsByCourse(@PathVariable Long courseId) {
        List<Lesson> lessons = lessonService.getLessonsByCourse(courseId);
        return ResponseEntity.ok(lessons);
    }


}
