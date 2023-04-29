package com.bipin.courseinfo.server;

import com.bipin.courseinfo.domain.Course;
import com.bipin.courseinfo.repository.CourseRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Path("/courses")
public class CourseResource {
    private static final Logger LOG = LoggerFactory.getLogger(CourseResource.class);
    private final CourseRepository courseRepository;

    public CourseResource(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourses() {
        return courseRepository.getAllCourses()
                .stream()
                .sorted(Comparator.comparing(Course::id))
                .toList();
    }
    @POST
    @Path("/{id}/notes")
    @Consumes(MediaType.TEXT_PLAIN)
    public void addNotes(@PathParam("id") String id, String notes)
    {
        courseRepository.addNotes(id, notes);
    }

}
