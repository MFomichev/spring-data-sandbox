package xyz.fomichev.sandbox.service;

import java.time.Instant;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.fomichev.sandbox.TestDataCreator;
import xyz.fomichev.sandbox.TransactionalBaseTest;
import xyz.fomichev.sandbox.model.Book;
import xyz.fomichev.sandbox.model.BookInstance;
import xyz.fomichev.sandbox.model.ReadingSession;
import xyz.fomichev.sandbox.model.Student;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static xyz.fomichev.sandbox.TestDataFactory.aNewBookInstance;
import static xyz.fomichev.sandbox.TestDataFactory.aReadingSession;

class StudentServiceTest extends TransactionalBaseTest {

    @Autowired
    private TestDataCreator testDataCreator;
    @Autowired
    private StudentService studentService;

    private Student student;
    private Book book;
    private BookInstance bookInstance;
    private BookInstance anotherInstance;
    private ReadingSession endReadingSession;
    @Autowired
    EntityManager entityManager;

    @BeforeEach
    void setUp() {
        this.student = testDataCreator.student();
        this.book = testDataCreator.book();
        this.bookInstance = testDataCreator.bookInstance(this.book);
        this.anotherInstance = testDataCreator.bookInstance(aNewBookInstance(this.book).build());
        this.endReadingSession = testDataCreator.readingSession(
                aReadingSession(this.student, this.bookInstance)
                        .endTime(Instant.ofEpochMilli(9999999))
                        .build());
    }

    @Test
    void testStartRead() {
        var readingSession1 = studentService.startRead(student.getId(), book.getId());
        var readingSession2 = studentService.startRead(student.getId(), book.getId());
        assertEquals(this.student.getId(), readingSession1.getStudent().getId());
        assertEquals(this.student.getId(), readingSession2.getStudent().getId());
        assertEquals(Set.of(this.bookInstance.getId(), this.anotherInstance.getId()),
                Set.of(readingSession1.getBookInstance().getId(), readingSession2.getBookInstance().getId())
        );
        assertThrows(IllegalArgumentException.class, () -> studentService.startRead(student.getId(), book.getId()));
    }

}
