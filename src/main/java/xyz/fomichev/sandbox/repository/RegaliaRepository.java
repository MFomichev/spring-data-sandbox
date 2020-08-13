package xyz.fomichev.sandbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.fomichev.sandbox.model.Author;
import xyz.fomichev.sandbox.model.Regalia;

import java.util.UUID;

public interface RegaliaRepository extends JpaRepository<Regalia, UUID> {
}
