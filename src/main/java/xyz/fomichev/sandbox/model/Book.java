package xyz.fomichev.sandbox.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.Value;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Book {

    @Id
    @NonNull
    private UUID id;

    @NonNull
    private String title;

    private String description;

}
