package xyz.fomichev.sandbox.service;

import java.time.Instant;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.fomichev.sandbox.model.ReadingSession;
import xyz.fomichev.sandbox.repository.BookInstanceRepository;
import xyz.fomichev.sandbox.repository.BookRepository;
import xyz.fomichev.sandbox.repository.ReadingSessionRepository;
import xyz.fomichev.sandbox.repository.StudentRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;
    private final BookInstanceRepository bookInstanceRepository;
    private final ReadingSessionRepository readingSessionRepository;

    public ReadingSession startRead(@NonNull UUID studentId, @NonNull UUID bookId) {
        var student = studentRepository.findById(studentId).orElseThrow();
        var book = bookRepository.findById(bookId).orElseThrow();
        //TODO: lock on book must be here
        var bookInstance = bookInstanceRepository.findFirstFreeInstanceByBookId(book.getId())
                .orElseThrow(() -> new IllegalArgumentException("No free books"));

        ReadingSession readingSession = new ReadingSession();
        readingSession.setId(UUID.randomUUID());
        readingSession.setStartTime(Instant.now());
        readingSession.setStudent(student);
        readingSession.setBookInstance(bookInstance);

        return this.readingSessionRepository.save(readingSession);
    }
}
