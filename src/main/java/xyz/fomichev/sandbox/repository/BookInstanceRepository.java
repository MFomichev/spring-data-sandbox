package xyz.fomichev.sandbox.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.fomichev.sandbox.model.BookInstance;

public interface BookInstanceRepository extends JpaRepository<BookInstance, UUID> {

    //TODO: doesn't check that book is free
    Optional<BookInstance> findFirstFreeInstanceByBookId(UUID bookId);
}
