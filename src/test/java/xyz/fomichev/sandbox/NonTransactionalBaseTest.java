package xyz.fomichev.sandbox;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class NonTransactionalBaseTest extends BaseTest {

    @Autowired
    List<CrudRepository<?, ?>> repositories;

    @AfterEach
    void tearDown() {
        repositories.forEach(CrudRepository::deleteAll);
    }
}
