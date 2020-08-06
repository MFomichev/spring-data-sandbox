package xyz.fomichev.sandbox;

import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;
import xyz.fomichev.sandbox.repository.BookInstanceRepository;

@ActiveProfiles("test")
@SpringBootTest(classes = ApplicationConfiguration.class)
public class BaseTest {

    @Configuration
    public static class MockRepositoryConfiguration {

        @Primary
        @Bean(name = "bookInstanceRepositoryMock")
        public BookInstanceRepository bookInstanceRepository(BookInstanceRepository bookInstanceRepository) {
            // workaround for https://github.com/spring-projects/spring-boot/issues/7033
            return Mockito.mock(BookInstanceRepository.class, AdditionalAnswers.delegatesTo(bookInstanceRepository));
        }
    }

}
