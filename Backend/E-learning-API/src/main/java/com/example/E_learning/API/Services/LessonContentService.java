package com.example.E_learning.API.Services;

import com.example.E_learning.API.Models.Lesson;
import com.example.E_learning.API.Models.LessonContent;
import com.example.E_learning.API.Repository.LessonContentRepo;
import com.example.E_learning.API.Repository.LessonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LessonContentService {

    @Autowired
    private LessonContentRepo lessonContentRepo;

    @Autowired
    private LessonRepo lessonRepo;

    public List<LessonContent> getContentsByLesson(Long lessonId) {
        return lessonContentRepo.findByLesson_LessonIdOrderByDisplayOrderAsc(lessonId);
    }


    @Transactional
    public LessonContent createLessonContent(Long lessonId, String contentType, String contentData, Integer displayOrder) {
        Lesson lesson = lessonRepo.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found"));

        LessonContent content = new LessonContent();
        content.setLesson(lesson);
        content.setContentType(contentType);
        content.setContentData(contentData);
        content.setDisplayOrder(displayOrder);
        content.setCreatedAt(LocalDateTime.now());

        return lessonContentRepo.save(content);
    }
}
