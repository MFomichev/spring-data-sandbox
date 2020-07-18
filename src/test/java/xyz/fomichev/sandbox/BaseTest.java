package xyz.fomichev.sandbox;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import xyz.fomichev.sandbox.repository.AuthorRepository;
import xyz.fomichev.sandbox.repository.BookRepository;
import xyz.fomichev.sandbox.service.AuthorService;
import xyz.fomichev.sandbox.service.BookService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(classes = ApplicationConfiguration.class)
public class BaseTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        assertEquals(1, context.getBeansOfType(BookRepository.class).size());
        assertEquals(1, context.getBeansOfType(BookService.class).size());

        assertEquals(1, context.getBeansOfType(AuthorRepository.class).size());
        assertEquals(1, context.getBeansOfType(AuthorService.class).size());
    }
}
