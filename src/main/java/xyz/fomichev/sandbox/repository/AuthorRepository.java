package xyz.fomichev.sandbox.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import xyz.fomichev.sandbox.model.Author;

import javax.persistence.LockModeType;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Optional<Author> findById(UUID uuid);
}
