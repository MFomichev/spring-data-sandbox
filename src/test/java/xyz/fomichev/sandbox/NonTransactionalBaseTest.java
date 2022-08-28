package xyz.fomichev.sandbox;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;
import xyz.fomichev.sandbox.repository.*;

import javax.persistence.EntityManager;

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
    @Autowired
    public ClientRepository clientRepository;
    @Autowired
    public TransactionTemplate transactionTemplate;
    @Autowired
    public EntityManager entityManager;

    @AfterEach
    void tearDown() {
        readingSessionRepository.deleteAll();
        bookInstanceRepository.deleteAll();
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        studentRepository.deleteAll();
        clientRepository.deleteAll();
    }
}
