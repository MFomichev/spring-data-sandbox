package xyz.fomichev.sandbox.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.fomichev.sandbox.BaseTest;
import xyz.fomichev.sandbox.model.Student;
import xyz.fomichev.sandbox.repository.StudentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static xyz.fomichev.sandbox.TestDataFactory.aStudent;

class StudentServiceTest extends BaseTest {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        Student student = aStudent().build();
        this.student = studentRepository.save(student);
    }

    @Test
    void testStartRead() {
        var readingSession = studentService.startRead(student.getId());
        assertEquals(this.student.getId(), readingSession.getStudent().getId());
    }

}
