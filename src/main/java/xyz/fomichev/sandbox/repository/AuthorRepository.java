package xyz.fomichev.sandbox.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.fomichev.sandbox.model.Author;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
