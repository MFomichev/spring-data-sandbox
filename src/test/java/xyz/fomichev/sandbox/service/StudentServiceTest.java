package xyz.fomichev.sandbox.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.fomichev.sandbox.BaseTest;
import xyz.fomichev.sandbox.TestDataCreator;
import xyz.fomichev.sandbox.model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentServiceTest extends BaseTest {

    @Autowired
    private TestDataCreator testDataCreator;
    @Autowired
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        this.student = testDataCreator.student();
    }

    @Test
    void testStartRead() {
        var readingSession = studentService.startRead(student.getId());
        assertEquals(this.student.getId(), readingSession.getStudent().getId());
    }

}
