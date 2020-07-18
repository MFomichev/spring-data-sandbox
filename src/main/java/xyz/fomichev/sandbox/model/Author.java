package xyz.fomichev.sandbox.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.NonNull;

@Entity
public class Author {
    @Id
    @NonNull
    private UUID id;

    @Column(name = "last_name")
    private String lastName;
}
