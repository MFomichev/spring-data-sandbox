package xyz.fomichev.sandbox.model;

import java.time.Instant;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ReadingSession {

    @Id
    @NonNull
    private UUID id;

    @NonNull
    private Instant startTime;

    private Instant endTime;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "book_instance_id")
    private BookInstance bookInstance;
}
