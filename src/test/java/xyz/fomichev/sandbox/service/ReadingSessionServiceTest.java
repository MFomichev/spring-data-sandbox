package xyz.fomichev.sandbox.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import liquibase.pro.packaged.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.fomichev.sandbox.BaseTest;
import xyz.fomichev.sandbox.TestDataCreator;
import xyz.fomichev.sandbox.model.Book;
import xyz.fomichev.sandbox.model.BookInstance;
import xyz.fomichev.sandbox.model.ReadingSession;
import xyz.fomichev.sandbox.model.Student;
import xyz.fomichev.sandbox.repository.ReadingSessionRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadingSessionServiceTest extends BaseTest {

    private UUID readingSessionId = UUID.fromString("ef76da29-9e8a-4c49-a7e5-93c926521198");
    private Instant readingSessionStartDate = Instant.parse("2007-12-03T23:59:59.999999999Z");

    @Autowired
    private ReadingSessionRepository readingSessionRepository;
    @Autowired
    private TestDataCreator testDataCreator;
    @Autowired
    private EntityManager entityManager;

    private BookInstance bookInstance;
    private Student student;

    @BeforeEach
    void setUp() {
        var book = testDataCreator.book();
        this.bookInstance = testDataCreator.bookInstance(book);
        this.student = testDataCreator.student();
    }

    @Test
    void testInstanceAccuracy() {
        var session = new ReadingSession();
        session.setId(readingSessionId);
        session.setStartTime(readingSessionStartDate);
        session.setBookInstance(this.bookInstance);
        session.setStudent(this.student);
        var persistedSession = readingSessionRepository.save(session);
        entityManager.flush();
        entityManager.refresh(persistedSession);
        assertEquals(session.getId(), persistedSession.getId());
        assertEquals(session.getStartTime().truncatedTo(ChronoUnit.MICROS), persistedSession.getStartTime());
    }
}
