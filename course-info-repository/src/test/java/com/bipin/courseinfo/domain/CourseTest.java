package com.bipin.courseinfo.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    @Test
    void filled_Blank_Id_Should_Throw_Exception() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            Course course = new Course("", "name", 5, "url", Optional.empty());
        }, "IllegalArgumentException was expected");

        assertEquals("No value present!", thrown.getMessage());
    }

    @Test
    void filled_Blank_Name_Should_Throw_Exception() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            Course course = new Course("id", "", 5, "url",Optional.empty());
        }, "IllegalArgumentException was expected");

        assertEquals("No value present!", thrown.getMessage());
    }
    @Test
    void filled_Blank_Url_Should_Throw_Exception() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            Course course = new Course("id", "name", 5, "",Optional.empty());
        }, "IllegalArgumentException was expected");

        assertEquals("No value present!", thrown.getMessage());
    }
    @Test
    void filled_No_Exception() {
        assertDoesNotThrow(() -> {
            Course course = new Course("id", "name", 5, "url",Optional.empty());
        }, "IllegalArgumentException was not expected");
    }
}