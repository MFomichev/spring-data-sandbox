package xyz.fomichev.sandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Regalia implements RootAware<Author> {

    @Id
    @NonNull
    @Column(name = "id")
    private UUID id;

    @NonNull
    @Column(name = "title")
    private String title;

    @JoinColumn(name = "author_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;

    @Override
    public Author root() {
        return getAuthor();
    }
}
