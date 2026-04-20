package com.example.E_learning.API.Services;

import com.example.E_learning.API.Models.Course;
import com.example.E_learning.API.Models.Creator;
import com.example.E_learning.API.Repository.CourseRepo;
import com.example.E_learning.API.Repository.CreatorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private CreatorRepo creatorRepo;

    public Course createCourse(Long creatorId, String title, String description, String thumbnailUrl) {
        Creator creator = creatorRepo.findById(creatorId)
                .orElseThrow(() -> new IllegalArgumentException("Creator not found"));

        Course course = new Course();
        course.setTitle(title);
        course.setDescription(description);
        course.setThumbnailUrl(thumbnailUrl);
        course.setCreatedAt(LocalDateTime.now());
        course.setCreator(creator);
        return courseRepo.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

}
