package xyz.fomichev.sandbox;

import java.util.UUID;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import xyz.fomichev.sandbox.model.Book;
import xyz.fomichev.sandbox.repository.BookRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
public class BookRepositoryTest extends BaseTest {

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
