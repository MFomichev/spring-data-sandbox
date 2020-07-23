package xyz.fomichev.sandbox;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Rollback
@Transactional
@ActiveProfiles("test")
@SpringBootTest(classes = ApplicationConfiguration.class)
public class BaseTest {

}
