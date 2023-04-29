package com.bipin.courseinfo.cli.service;

import com.bipin.courseinfo.domain.Course;
import com.bipin.courseinfo.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

public class CourseStorageService {
    private static final String PS_BASE_URL="https://app.pluralsight.com";
    private final CourseRepository courseRepository;

    public CourseStorageService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void storePluralsightCourses(List<PluralsightCourse> pluralsightCourses)
    {
        for(PluralsightCourse pluralsightCourse:pluralsightCourses)
        {
            Course course=new Course(pluralsightCourse.id(),
                    pluralsightCourse.title(),
                    pluralsightCourse.durationInMinutes(),
                    PS_BASE_URL + pluralsightCourse.contentUrl(),
                    Optional.empty());
            courseRepository.saveCourse(course);
        }
    }
}
