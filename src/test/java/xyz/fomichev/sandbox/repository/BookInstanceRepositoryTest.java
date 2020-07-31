package xyz.fomichev.sandbox.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.fomichev.sandbox.BaseTest;
import xyz.fomichev.sandbox.TestDataCreator;
import xyz.fomichev.sandbox.model.Book;
import xyz.fomichev.sandbox.model.BookInstance;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static xyz.fomichev.sandbox.TestDataFactory.aBookInstance;


class BookInstanceRepositoryTest extends BaseTest {

    @Autowired
    BookInstanceRepository bookInstanceRepository;
    @Autowired
    TestDataCreator testDataCreator;

    Book book;

    @BeforeEach
    void setUp() {
        this.book = testDataCreator.book();
    }

    @Test
    void testVersionCreate() {
        BookInstance persistedBookInstance = bookInstanceRepository.saveAndFlush(aBookInstance(book).build());
        assertNotNull(persistedBookInstance.getVersion());
    }
}
