package xyz.fomichev.sandbox.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;
import xyz.fomichev.sandbox.NonTransactionalBaseTest;
import xyz.fomichev.sandbox.TestDataCreator;
import xyz.fomichev.sandbox.model.Book;
import xyz.fomichev.sandbox.model.BookInstance;
import xyz.fomichev.sandbox.model.Student;
import xyz.fomichev.sandbox.repository.BookInstanceRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static xyz.fomichev.sandbox.TestDataFactory.aStudent;

class StudentServiceNonTranTest extends NonTransactionalBaseTest {

    public static final UUID SECOND_STUDENT_ID = UUID.fromString("ef76da29-9e8a-4c49-a7e5-93c926593422");

    @Autowired
    ObjectProvider<BookInstanceRepository> bookInstanceRepository;

    @Autowired
    StudentService studentService;
    @Autowired
    TestDataCreator testDataCreator;
    @Autowired
    TransactionTemplate transactionTemplate;

    Student studentOne;
    Student studentTwo;
    Book book;
    BookInstance bookInstance;

    @BeforeEach
    void setUp() {
        this.studentOne = testDataCreator.student();
        this.studentTwo = testDataCreator.student(aStudent().id(SECOND_STUDENT_ID).build());
        this.book = testDataCreator.book();
        this.bookInstance = testDataCreator.bookInstance(this.book);
    }

    @Test
    void testStartReadForConcurrentInstanceBook() throws Exception {
        Object finishFirstTransaction = new Object();
        CountDownLatch readLatch = new CountDownLatch(2);
        var bookInstanceRepositoryMock = this.bookInstanceRepository.getIfAvailable();
        when(bookInstanceRepositoryMock.findFreeInstanceByBookId(any()))
                .thenAnswer((Answer<Optional<BookInstance>>) invocation -> {
                    Optional<BookInstance> result = (Optional<BookInstance>) invocation.callRealMethod();
                    readLatch.countDown();
                    readLatch.await();
                    synchronized (finishFirstTransaction) {
                        return result;
                    }
                });
        var transaction1 = CompletableFuture.runAsync(() -> {
            synchronized (finishFirstTransaction) {
                    studentService.startRead(studentOne.getId(), book.getId());
            }
        });
        var transaction2 = CompletableFuture.runAsync(() -> {
                studentService.startRead(studentTwo.getId(), book.getId());
        });
        transaction1.get();
        try {
            transaction2.get();
            fail();
        } catch (ExecutionException e) {
            if (e.getCause().getClass() != IllegalArgumentException.class) {
                fail(e);
            }
        } catch (Throwable e) {
            fail(e);
        }
    }
}
