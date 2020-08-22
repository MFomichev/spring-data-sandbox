package xyz.fomichev.sandbox.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;
import xyz.fomichev.sandbox.NonTransactionalBaseTest;
import xyz.fomichev.sandbox.model.Author;
import xyz.fomichev.sandbox.model.Regalia;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static xyz.fomichev.sandbox.TestDataFactory.anAuthor;

class AuthorRepositoryNonTranTest extends NonTransactionalBaseTest {

    public static final String REGALIA_ID_1 = "ef76da29-9e8a-4c49-a7e5-93c926522992";
    public static final String REGALIA_TITLE_1 = "Regalia title1";
    public static final String REGALIA_ID_2 = "ef76da29-9e8a-4c49-a7e5-93c9265229AF";
    public static final String REGALIA_TITLE_2 = "Regalia title2";
    @Autowired
    TransactionTemplate transactionTemplate;
    @Autowired
    AuthorRepository authorRepository;

    @Test
    void testVersionIncrement() {
        Author initialAuthor = transactionTemplate.execute(status -> authorRepository.save(anAuthor().build()));
        int initialVersion = initialAuthor.getVersion();
        var updatedAuthor = transactionTemplate.execute(status -> {
            Author author = authorRepository.findById(initialAuthor.getId()).orElseThrow();
            author.addRegalia(Regalia.builder().id(UUID.fromString(REGALIA_ID_1)).title(REGALIA_TITLE_1).build());
            author.addRegalia(Regalia.builder().id(UUID.fromString(REGALIA_ID_2)).title(REGALIA_TITLE_2).build());
            return authorRepository.save(author);
        });
        transactionTemplate.executeWithoutResult(status -> {
            assertThat(authorRepository.getOne(initialAuthor.getId()).getRegalias()).hasSize(2);
        });
        assertEquals(1, updatedAuthor.getVersion() - initialVersion);
    }
}
