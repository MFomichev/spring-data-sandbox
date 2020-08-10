package xyz.fomichev.sandbox;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import xyz.fomichev.sandbox.model.BookInstance;
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
