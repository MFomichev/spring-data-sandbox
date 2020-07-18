package xyz.fomichev.sandbox;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import xyz.fomichev.sandbox.repository.AuthorRepository;
import xyz.fomichev.sandbox.repository.BookRepository;
import xyz.fomichev.sandbox.repository.StudentRepository;
import xyz.fomichev.sandbox.service.AuthorService;
import xyz.fomichev.sandbox.service.BookService;
import xyz.fomichev.sandbox.service.StudentService;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@Rollback
//@Transactional
@ActiveProfiles("test")
@SpringBootTest(classes = ApplicationConfiguration.class)
public class BaseTest {

}
