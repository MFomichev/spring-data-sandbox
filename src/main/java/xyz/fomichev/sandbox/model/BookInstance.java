package xyz.fomichev.sandbox.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class BookInstance {

    @Id
    @NonNull
    private UUID id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Quality quality;

    @Version
    private Integer version;

}
