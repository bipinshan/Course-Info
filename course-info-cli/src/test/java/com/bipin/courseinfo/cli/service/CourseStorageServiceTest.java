package com.bipin.courseinfo.cli.service;

import com.bipin.courseinfo.domain.Course;
import com.bipin.courseinfo.repository.CourseRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CourseStorageServiceTest {

    @Test
    void storePluralsightCourses() {
        CourseRepository courseRepository=new InMemoryCourseRepository();
        CourseStorageService courseStorageService=new CourseStorageService(courseRepository);

        PluralsightCourse psCourse=new PluralsightCourse("1","Title-1",
                "01:40:54.9613330","/url-1",false);
        courseStorageService.storePluralsightCourses(List.of(psCourse));

        Course expected=new Course("1","Title-1",100,"https://app.pluralsight.com/url-1", Optional.empty());
        assertEquals(List.of(expected), courseRepository.getAllCourses());
    }

    static class InMemoryCourseRepository implements CourseRepository{
        private final List<Course> courses=new ArrayList<>();
        @Override
        public void saveCourse(Course course) {
            courses.add(course);
        }

        @Override
        public List<Course> getAllCourses() {
            return courses;
        }

        @Override
        public void addNotes(String id, String notes) {
            throw new UnsupportedOperationException();
        }
    }
}