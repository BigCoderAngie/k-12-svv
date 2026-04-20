# K-12 E-Learning Platform API

# Overview
**Purpose:** Facilitate the management of an E-Learning platform for K-12 education  
**Target Audience:** Students, Teachers, and Content Creators  
**Version:** v1  

# Authentication  
**Authentication type:** Spring Security with BCrypt password hashing
  
**Purpose for chosen Authentication type:** Provides secure password storage and stateless authentication suitable for REST APIs.

# Endpoint Summary and Details

## Authentication Endpoints

1. User Registration
**Endpoint:** POST /api/auth/signup
```json
{
    "email": "user@example.com",
    "username": "username",
    "password": "password",
    "role": "STUDENT|CREATOR|MODERATOR",
    "bio": "User biography" 
}
```

2. User Login
**Endpoint:** POST /api/auth/login
```json
{
    "email": "user@example.com",
    "password": "password"
}
```

Response:
```json
{
    "id": 1,
    "email": "user@example.com",
    "role": "STUDENT|CREATOR|MODERATOR"
}
```

## Course Endpoints

1. Create Course
**Endpoint:** POST /api/courses/create
```json
{
    "creatorId": 1,
    "title": "Course Title",
    "description": "Course Description",
    "thumbnailUrl": "http://example.com/thumbnail.jpg"
}
```

Response:
```json
{
    "courseId": 1,
    "title": "Course Title",
    "description": "Course Description",
    "createdAt": "2025-05-05T10:00:00",
    "creator": {
        "creatorId": 1
    }
}
```

2. Get All Courses
**Endpoint:** GET /api/courses/all

## Lesson Endpoints

1. Create Lesson
**Endpoint:** POST /api/lessons/create
```json
{
    "courseId": 1,
    "title": "Lesson Title",
    "description": "Lesson Description"
}
```

2. Get Lesson by ID
**Endpoint:** GET /api/lessons/{lessonId}

3. Get Lessons by Course
**Endpoint:** GET /api/lessons/course/{courseId}

4. Create Lesson Content
**Endpoint:** POST /api/lesson-contents/create
```json
{
    "lessonId": 1,
    "contentType": "text|image|url|file",
    "contentData": "JSON string containing content",
    "displayOrder": 1
}
```

5. Get Lesson Contents
**Endpoint:** GET /api/lesson-contents/lesson/{lessonId}

## Enrollment Endpoints

1. Enroll in Course
**Endpoint:** POST /api/enrollments/enroll
```json
{
    "studentId": 1,
    "courseId": 1
}
```

2. Get Enrolled Courses
**Endpoint:** GET /api/enrollments/student/{studentId}

# Error Handling
**Error format:**
```json
{
    "error": "integer",
    "message": "string"
}
```

**Common Error Types:**
- **400 Bad Request:** Invalid input or missing required fields
- **401 Unauthorized:** Authentication failure
- **409 Conflict:** Resource already exists (e.g., duplicate email)
- **404 Not Found:** Requested resource not found
- **500 Internal Server Error:** Server-side error

# Security Configuration
- CORS enabled for http://localhost:5173 (React dev server)
- All endpoints under /api/** are currently accessible for development
- Password encryption using BCrypt
- Session management set to STATELESS
