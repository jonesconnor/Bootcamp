package com.stackroute.spring.test;

import com.stackroute.spring.config.AppConfig;
import com.stackroute.spring.model.Course;
import com.stackroute.spring.model.Professor;
import com.stackroute.spring.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit test for annotation based configuration.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
public class AppTest {
    private static final String MESSAGE_ONE = "Beans should be created given proper annotations";
    private static final String MESSAGE_TWO = "Beans should be injected given proper annotations";

    @Autowired
    private Student student;

    @Autowired
    private Professor professor;

    @Autowired
    private Course course;

    @Test
    public void givenProperAnnotationsThenBeansCreated() {
        assertNotNull(student, MESSAGE_ONE);
        assertNotNull(professor,MESSAGE_ONE);
        assertNotNull(course,MESSAGE_ONE);
    }
    @Test
    public void givenProperAnnotationsThenBeansAreInjectedProperly() {
        assertNotNull(student.getCourseList(), MESSAGE_ONE);
        assertNotNull(student.getCourseList().get(0).getCourseName(), MESSAGE_TWO);
        assertNotNull(professor.getEmployeeId(), MESSAGE_TWO);
        assertNotNull(professor.getProfessorName(), MESSAGE_TWO);
        assertNotNull(professor.getCourse(),MESSAGE_TWO);
        assertNotNull(course.getCourseId(), MESSAGE_TWO);
        assertNotNull(course.getCourseName(), MESSAGE_TWO);
        assertNotNull(course.getStudentList(),MESSAGE_TWO);
    }
}

