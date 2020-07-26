package xyz.fomichev.sandbox.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.fomichev.sandbox.BaseTest;
import xyz.fomichev.sandbox.model.ReadingSession;
import xyz.fomichev.sandbox.repository.ReadingSessionRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadingSessionServiceTest extends BaseTest {

    private UUID readingSessionId = UUID.fromString("ef76da29-9e8a-4c49-a7e5-93c926521198");
    private Instant readingSessionStartDate = Instant.parse("2007-12-03T23:59:59.999999999Z");

    @Autowired
    private ReadingSessionRepository readingSessionRepository;

    @Test
    void testInstanceAccuracy() {
        var session = new ReadingSession();
        session.setId(readingSessionId);
        session.setStartTime(readingSessionStartDate);
        readingSessionRepository.save(session);
        var persistedSession = readingSessionRepository.findById(readingSessionId).orElseThrow();
        assertEquals(session.getId(), persistedSession.getId());
        assertEquals(session.getStartTime().truncatedTo(ChronoUnit.MICROS), persistedSession.getStartTime());
    }
}
