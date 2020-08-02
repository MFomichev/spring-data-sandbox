package xyz.fomichev.sandbox.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xyz.fomichev.sandbox.model.BookInstance;

public interface BookInstanceRepository extends JpaRepository<BookInstance, UUID> {

    @Query(value =
            "FROM BookInstance bookInstance " +
                    "JOIN bookInstance.book book " +
                    "LEFT JOIN ReadingSession readingSession ON readingSession.bookInstance = bookInstance " +
                    "WHERE bookInstance.book.id = :bookId " +
                    "AND readingSession IS NULL")
    List<BookInstance> findFreeInstancesByBookId(UUID bookId, Pageable pageable);

    default Optional<BookInstance> findFreeInstanceByBookId(UUID bookId) {
        return findFreeInstancesByBookId(bookId, PageRequest.of(0, 1)).stream().findAny();
    }
}
