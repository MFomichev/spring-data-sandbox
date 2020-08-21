package xyz.fomichev.sandbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.fomichev.sandbox.model.Author;

import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    Optional<Author> findById(UUID uuid);

}
