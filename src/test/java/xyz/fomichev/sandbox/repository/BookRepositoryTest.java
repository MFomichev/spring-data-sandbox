package xyz.fomichev.sandbox.repository;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.fomichev.sandbox.TransactionalBaseTest;
import xyz.fomichev.sandbox.model.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookRepositoryTest extends TransactionalBaseTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testAInsert() {
        Book book = new Book();
        book.setId(UUID.randomUUID());
        book.setTitle("My best book");
        book.setDescription("About life");
        Book persistedBook = bookRepository.save(book);
        assertEquals(book, persistedBook);
        assertEquals(book, bookRepository.findById(book.getId()).orElseThrow());
    }

}
