package xyz.fomichev.sandbox;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = ApplicationConfiguration.class)
public class BaseTest {


}
