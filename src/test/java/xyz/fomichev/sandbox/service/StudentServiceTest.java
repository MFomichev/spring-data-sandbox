package xyz.fomichev.sandbox.service;

import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.fomichev.sandbox.BaseTest;
import xyz.fomichev.sandbox.model.Student;
import xyz.fomichev.sandbox.repository.StudentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentServiceTest extends BaseTest {

    private static final UUID STUDENT_ID = UUID.fromString("ef76da29-9e8a-4c49-a7e5-73c926558222");
    private static final String LAST_NAME = "Best last name";

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        Student student = new Student();
        student.setId(STUDENT_ID);
        student.setLastName(LAST_NAME);
        this.student = studentRepository.save(student);
    }

    @Test
    void testStartRead() {
        var readingSession = studentService.startRead(student.getId());
        assertEquals(this.student.getId(), readingSession.getStudent().getId());
    }

}
