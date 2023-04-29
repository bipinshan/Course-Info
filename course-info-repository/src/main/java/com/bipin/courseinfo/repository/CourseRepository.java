package com.bipin.courseinfo.repository;

import com.bipin.courseinfo.domain.Course;

import java.util.List;

public interface CourseRepository {
    static CourseRepository openCourseRepository(String databaseFile) {
        return new CourseJdbcRepository(databaseFile);
    }

    void saveCourse(Course course);

    List<Course> getAllCourses();

    void addNotes(String id, String notes);
}
