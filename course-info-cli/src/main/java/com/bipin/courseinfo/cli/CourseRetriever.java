package com.bipin.courseinfo.cli;

import com.bipin.courseinfo.cli.service.CourseRetrievalService;
import com.bipin.courseinfo.cli.service.CourseStorageService;
import com.bipin.courseinfo.cli.service.PluralsightCourse;
import com.bipin.courseinfo.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.function.Predicate;

public class CourseRetriever {
    private static final Logger LOG= LoggerFactory.getLogger(CourseRetriever.class);
    public static void main(String[] args) {
        LOG.info("CourseRetriever started!");
        if (args.length == 0) {
            LOG.warn("Please provide an author name as first parameter.");
            return;
        }
        try {
            retrieveCourses(args[0]);
        }
        catch (Exception e)
        {
            LOG.error("Unexpected error", e);
        }
    }

    private static void retrieveCourses(String authorId) {
        LOG.info("Retrieving courses for author '{}'", authorId);
        CourseRetrievalService courseRetrievalService=new CourseRetrievalService();
        CourseRepository courseRepository=CourseRepository.openCourseRepository("./courses.db");
        CourseStorageService courseStorageService=new CourseStorageService(courseRepository);
        List<PluralsightCourse> coursesToStore=courseRetrievalService.getCoursesFor(authorId)
                .stream()
                .filter(Predicate.not(PluralsightCourse::isRetired))
                //.filter(course -> !course.isRetired())
                .toList();
        LOG.info("Retrieved the following {} courses {}",coursesToStore.size(),coursesToStore);
        courseStorageService.storePluralsightCourses(coursesToStore);
        LOG.info("Courses successfully stored");
    }
}
