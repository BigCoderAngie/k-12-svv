CREATE TYPE UserT AS(
    Username VARCHAR(50),
    Email VARCHAR(255),
    Password_hash VARCHAR(255),
    is_Banned BOOLEAN,
    Created_at TIMESTAMP
);
CREATE TABLE Student(
    Student_id BIGSERIAL PRIMARY KEY,
    Details UserT,
    BIO TEXT
);
CREATE TABLE ADMIN(
    Adimn_id SERIAL PRIMARY KEY,
    Details UserT
);
CREATE TABLE MODERATOR(
    Moderator_id BIGSERIAL PRIMARY KEY,
    Details UserT 
);
CREATE TABLE CREATOR(
    Creator_id BIGSERIAL PRIMARY KEY,
    Details UserT,
    BIO TEXT
);
CREATE TABLE Searchquery(
    Search_id BIGSERIAL PRIMARY KEY,
    Student_id BIGSERIAL,
    query_text TEXT,
    Searched_at TIMESTAMP,
    FOREIGN KEY(Student_id) references Student(Student_id)
);
CREATE TABLE Course(
    Course_id BIGSERIAL PRIMARY KEY,
    title VARCHAR(150),
    description TEXT,
    Creator_id BIGSERIAL,
    Created_at TIMESTAMP,
    Total_likes BIGINT,
    Total_reviews BIGINT,
    thumbnail_url VARCHAR(255),
    FOREIGN KEY(Creator_id) references CREATOR(Creator_id)
);
CREATE TABLE Lesson(
    Lesson_id BIGSERIAL PRIMARY KEY,
    Course_id BIGSERIAL,
    title VARCHAR(150),
    Description Text,
    is_flagged BOOLEAN,
    FOREIGN KEY(Course_id) references Course(Course_id)
);
CREATE TABLE STUDENTPROGRESS(
    progress_id BIGSERIAL PRIMARY KEY,
    Student_id BIGSERIAL,
    Course_id BIGSERIAL,
    Lesson_id BIGINT,
    Last_Viewed TIMESTAMP,
    FOREIGN KEY (Student_id) references Student(Student_id),
    FOREIGN KEY (Course_id) references Course(Course_id),
    FOREIGN KEY(Lesson_id) references Lesson(Lesson_id)  
);
CREATE TYPE Usertype AS ENUM(
    'MODERATOR','CREATOR','STUDENT'
);
CREATE TABLE ADMINACTION(
    Action_id BIGSERIAL PRIMARY KEY,
    Adimn_id SERIAL,
    action_type VARCHAR(50),
    Target_user_Type Usertype,
    Target_user_id BIGSERIAL 
);
CREATE TABLE Flag(
    flag_id BIGSERIAL PRIMARY KEY,
    Moderator_id BIGINT,
    Lesson_id BIGINT,
    reason TEXT,
    Is_reviewed BOOLEAN,
    flagged_at TIMESTAMP,
    FOREIGN KEY(Moderator_id) references MODERATOR(Moderator_id),
    FOREIGN KEY(Lesson_id) references Lesson(Lesson_id)
);
CREATE TABLE LessonAnalytics(
    Analytics_id BIGSERIAL PRIMARY KEY,
    Lesson_id BIGINT,
    viewerCount BIGINT,
    likeCount BIGINT,
    CommentCount BIGINT,
    Last_Viewed_at TIMESTAMP,
    FOREIGN KEY(Lesson_id) references Lesson(Lesson_id)
);
CREATE TABLE ENROLLMENT(
    Enrollmet_id BIGSERIAL PRIMARY KEY,
    Student_id BIGINT,
    Course_id BIGINT,
    Enrolled_at TIMESTAMP,
    FOREIGN KEY(Student_id) references STUDENT(Student_id),
    FOREIGN KEY(Course_id) references Course(Course_id)
);
CREATE TABLE Comment(
    Comment_id BIGSERIAL PRIMARY KEY,
    Student_id BIGINT,
    Lesson_id BIGINT,
    Comment_text TEXT,
    Commented_at TIMESTAMP,
    FOREIGN KEY(Student_id) references STUDENT(Student_id),
    FOREIGN KEY(Lesson_id) references Lesson(Lesson_id)
);
CREATE TABLE ContentLike(
    like_id BIGSERIAL PRIMARY KEY,
    Student_id BIGSERIAL,
    Lesson_id BIGSERIAL,
    like_at TIMESTAMP,
    FOREIGN KEY(Student_id) references STUDENT(Student_id),
    FOREIGN KEY(Lesson_id) references Lesson(Lesson_id)
);
CREATE Table tableLesson_Contents(
id SERIAL PRIMARY KEY,
Lesson_id BIGINT references Lesson(Lesson_id),
content_type TEXT CHECK (content_type IN ('text', 'image', 'url', 'file')),
content_data JSON,
display_order INTEGER,
created_at TIMESTAMP);