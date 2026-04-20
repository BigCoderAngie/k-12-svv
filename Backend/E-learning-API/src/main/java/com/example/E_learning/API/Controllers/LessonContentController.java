package com.example.E_learning.API.Controllers;

import com.example.E_learning.API.DTO.CreateLessonContentRequest;
import com.example.E_learning.API.Models.LessonContent;
import com.example.E_learning.API.Services.LessonContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lesson-contents")
public class LessonContentController {

    @Autowired
    private LessonContentService lessonContentService;

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<LessonContent>> getContentsByLesson(@PathVariable Long lessonId) {
        List<LessonContent> contents = lessonContentService.getContentsByLesson(lessonId);
        return ResponseEntity.ok(contents);
    }

    @PostMapping("/create")
    public ResponseEntity<LessonContent> createLessonContent(@RequestBody CreateLessonContentRequest request) {
        LessonContent content = lessonContentService.createLessonContent(
                request.getLessonId(),
                request.getContentType(),
                request.getContentData(),
                request.getDisplayOrder()
        );
        return ResponseEntity.ok(content);
    }
}
