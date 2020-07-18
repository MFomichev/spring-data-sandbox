package xyz.fomichev.sandbox.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Student {
    @Id
    @NonNull
    private UUID id;

    @Column(name = "last_name")
    private String lastName;

}
