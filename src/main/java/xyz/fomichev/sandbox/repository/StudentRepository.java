package xyz.fomichev.sandbox.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.fomichev.sandbox.model.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {
}
