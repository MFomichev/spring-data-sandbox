package xyz.fomichev.sandbox.service;

import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.fomichev.sandbox.model.ReadingSession;
import xyz.fomichev.sandbox.model.Student;
import xyz.fomichev.sandbox.repository.ReadingSessionRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {

    private final ReadingSessionRepository readingSessionRepository;

    public ReadingSession startRead(Student student) {
        ReadingSession readingSession = new ReadingSession();
        readingSession.setId(UUID.randomUUID());
        readingSession.setStartTime(Instant.now());
        readingSession.setStudent(student);
        return this.readingSessionRepository.save(readingSession);
    }
}
