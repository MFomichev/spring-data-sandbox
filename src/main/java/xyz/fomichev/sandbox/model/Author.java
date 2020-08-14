package xyz.fomichev.sandbox.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Author {
    @Id
    @NonNull
    private UUID id;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(
            mappedBy = "author",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Regalia> regalias = new ArrayList<>();

    @Version
    private Integer version;
}
