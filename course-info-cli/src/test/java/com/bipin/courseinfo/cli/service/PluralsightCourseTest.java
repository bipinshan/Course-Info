package com.bipin.courseinfo.cli.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PluralsightCourseTest {

    @org.junit.jupiter.api.Test
    void durationInMinutes() {
        PluralsightCourse pluralsightCourse =
                new PluralsightCourse("id", "title", "00:05:37", "url", false);
        assertEquals(5, pluralsightCourse.durationInMinutes());

    }

    @org.junit.jupiter.api.Test
    void durationInMinutesOverHour() {
        PluralsightCourse pluralsightCourse =
                new PluralsightCourse("id", "title", "01:08:54.9613330", "url", false);
        assertEquals(68, pluralsightCourse.durationInMinutes());

    }

    @org.junit.jupiter.api.Test
    void durationInMinutesOverHourZero() {
        PluralsightCourse pluralsightCourse =
                new PluralsightCourse("id", "title", "00:00:00", "url", false);
        assertEquals(0, pluralsightCourse.durationInMinutes());

    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            00:05:37,5
            00:00:00,0
            01:08:54.9613330,68
            """)
    void durationInMinutesParameterized(String input, long expected) {
        PluralsightCourse pluralsightCourse =
                new PluralsightCourse("id", "title", input, "url", false);
        assertEquals(expected, pluralsightCourse.durationInMinutes());

    }
}