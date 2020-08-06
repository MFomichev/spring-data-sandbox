package xyz.fomichev.sandbox.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import xyz.fomichev.sandbox.TransactionalBaseTest;
import xyz.fomichev.sandbox.repository.BookInstanceRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentServiceNonTranTest extends TransactionalBaseTest {

    @Autowired
    ApplicationContext context;

    @Test
    void testMock() {
        assertTrue(Mockito.mockingDetails(context.getBean(BookInstanceRepository.class)).isMock());
    }
}
