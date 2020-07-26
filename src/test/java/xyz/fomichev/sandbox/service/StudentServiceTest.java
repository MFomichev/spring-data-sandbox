package xyz.fomichev.sandbox.service;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.fomichev.sandbox.BaseTest;
import xyz.fomichev.sandbox.TestDataCreator;
import xyz.fomichev.sandbox.model.Book;
import xyz.fomichev.sandbox.model.BookInstance;
import xyz.fomichev.sandbox.model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static xyz.fomichev.sandbox.TestDataFactory.aNewBookInstance;

class StudentServiceTest extends BaseTest {

    @Autowired
    private TestDataCreator testDataCreator;
    @Autowired
    private StudentService studentService;

    private Student student;
    private Book book;
    private BookInstance bookInstance;
    private BookInstance anotherInstance;

    @BeforeEach
    void setUp() {
        this.student = testDataCreator.student();
        this.book = testDataCreator.book();
        this.bookInstance = testDataCreator.bookInstance(this.book);
        this.anotherInstance = testDataCreator.bookInstance(aNewBookInstance(this.book).build());
    }

    @Test
    void testStartRead() {
        var readingSession = studentService.startRead(student.getId(), book.getId());
        assertEquals(this.student.getId(), readingSession.getStudent().getId());
        assertTrue(Set.of(
                this.bookInstance.getId(),
                this.anotherInstance.getId())
                .contains(readingSession.getBookInstance().getId()));
    }

}
