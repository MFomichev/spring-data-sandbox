package xyz.fomichev.sandbox.model;

import java.time.Instant;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
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
}
