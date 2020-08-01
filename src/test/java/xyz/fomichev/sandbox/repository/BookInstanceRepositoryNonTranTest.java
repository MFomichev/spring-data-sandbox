package xyz.fomichev.sandbox.repository;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.transaction.support.TransactionTemplate;
import xyz.fomichev.sandbox.NonTransactionalBaseTest;
import xyz.fomichev.sandbox.TestDataCreator;
import xyz.fomichev.sandbox.model.Book;

import static org.junit.jupiter.api.Assertions.fail;
import static xyz.fomichev.sandbox.model.Quality.BAD;
import static xyz.fomichev.sandbox.model.Quality.EXCELLENT;

class BookInstanceRepositoryNonTranTest extends NonTransactionalBaseTest {

    @Autowired
    BookInstanceRepository bookInstanceRepository;
    @Autowired
    TestDataCreator testDataCreator;
    @Autowired
    TransactionTemplate transactionTemplate;

    Book book;

    @BeforeEach
    void setUp() {
        this.book = testDataCreator.book();
    }

    @Test
    void testOptimisticLock() throws Exception {
        var bookInstanceId = testDataCreator.bookInstance(this.book).getId();
        Semaphore semaphoreFirst = new Semaphore(0);
        Semaphore semaphoreSecond = new Semaphore(0);
        var transaction1 = CompletableFuture.runAsync(() ->
                transactionTemplate.executeWithoutResult(transactionStatus -> {
                    var bookInstance = bookInstanceRepository.findById(bookInstanceId).orElseThrow();
                    try {
                        semaphoreSecond.release();
                        semaphoreFirst.acquire();
                    } catch (InterruptedException e) {
                        fail();
                    }
                    bookInstance.setQuality(BAD);
                }));
        var transaction2 = CompletableFuture.runAsync(() ->
                transactionTemplate.executeWithoutResult(transactionStatus -> {
                    var bookInstance = bookInstanceRepository.findById(bookInstanceId).orElseThrow();
                    try {
                        semaphoreSecond.acquire();
                    } catch (InterruptedException e) {
                        fail();
                    }
                    bookInstance.setQuality(EXCELLENT);
                }));

        transaction2.get();
        semaphoreFirst.release();
        try {
            transaction1.get();
            fail();
        } catch (ExecutionException e) {
            if (e.getCause().getClass() != ObjectOptimisticLockingFailureException.class) {
                fail(e);
            }
        } catch (Throwable e) {
            fail(e);
        }
    }

}
