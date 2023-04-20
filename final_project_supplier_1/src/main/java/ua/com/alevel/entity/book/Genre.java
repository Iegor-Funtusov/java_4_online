package ua.com.alevel.entity.book;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.entity.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "genres")
public class Genre extends BaseEntity {

    private String type;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "genre_book",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> books;

    public Genre() {
        super();
        this.books = new HashSet<>();
    }
}
