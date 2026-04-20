package com.example.E_learning.API.Controllers;

import com.example.E_learning.API.DTO.CourseResponse;
import com.example.E_learning.API.DTO.CreateCourseRequest;
import com.example.E_learning.API.Models.Course;
import com.example.E_learning.API.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CreateCourseRequest request) {
        Course course = courseService.createCourse(
                request.getCreatorId(),
                request.getTitle(),
                request.getDescription(),
                request.getThumbnailUrl()
        );

        CourseResponse courseResponse = new CourseResponse(
                course.getCourseId(),
                course.getTitle(),
                course.getDescription(),
                course.getCreatedAt(),
                course.getCreator().getCreatorId()
        );

        return ResponseEntity.ok(courseResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }



}
