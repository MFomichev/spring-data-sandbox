package xyz.fomichev.sandbox.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.fomichev.sandbox.model.ReadingSession;

public interface ReadingSessionRepository extends JpaRepository<ReadingSession, UUID> {
}
