package com.example.E_learning.API.DTO;

import java.time.LocalDateTime;

public class CourseResponse {
    private Long courseId;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private CreatorIdOnly creator;

    public CourseResponse(Long courseId, String title, String description, LocalDateTime createdAt, Long creatorId) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.creator = new CreatorIdOnly(creatorId);
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public CreatorIdOnly getCreator() {
        return creator;
    }

    public static class CreatorIdOnly {
        private Long creatorId;

        public CreatorIdOnly(Long creatorId) {
            this.creatorId = creatorId;
        }

        public Long getCreatorId() {
            return creatorId;
        }
    }
}
