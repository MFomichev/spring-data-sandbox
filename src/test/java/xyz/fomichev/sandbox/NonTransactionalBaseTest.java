package xyz.fomichev.sandbox;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.fomichev.sandbox.repository.*;

public class NonTransactionalBaseTest extends BaseTest {

    @Autowired
    ReadingSessionRepository readingSessionRepository;
    @Autowired
    BookInstanceRepository bookInstanceRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    StudentRepository studentRepository;

    @AfterEach
    void tearDown() {
        readingSessionRepository.deleteAll();
        bookInstanceRepository.deleteAll();
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        studentRepository.deleteAll();
    }
}
