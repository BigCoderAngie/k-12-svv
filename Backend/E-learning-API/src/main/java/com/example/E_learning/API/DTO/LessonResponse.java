package com.example.E_learning.API.DTO;

public class LessonResponse {
    private Long lessonId;
    private String title;
    private String description;
    private Long courseId;
    private Long creatorId;

    public LessonResponse(Long lessonId, String title, String description, Long courseId, Long creatorId) {
        this.lessonId = lessonId;
        this.title = title;
        this.description = description;
        this.courseId = courseId;
        this.creatorId = creatorId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getCreatorId() {
        return creatorId;
    }
}
