package xyz.fomichev.sandbox.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;

import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @Builder.Default
    private List<Regalia> regalias = new ArrayList<>();

    @Version
    private Integer version;

    public void addRegalia(Regalia regalia) {
        getRegalias().add(regalia);
        regalia.setAuthor(this);
    }
}
