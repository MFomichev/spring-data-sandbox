package xyz.fomichev.sandbox.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.fomichev.sandbox.model.Book;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
