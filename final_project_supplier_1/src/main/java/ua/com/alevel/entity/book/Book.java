package ua.com.alevel.entity.book;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.entity.author.Author;
import ua.com.alevel.entity.publisher.Publisher;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "isbn", unique = true)
    private String isbn;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "page_size")
    private Integer pageSize;

    @Column(name = "publication_date")
    private Integer publicationDate;

    @Column(precision = 7, scale = 2)
    private BigDecimal price;

    private Integer quantity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Publisher publisher;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "books")
    private Set<Author> authors;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "books")
    private Set<Genre> genres;

    public Book() {
        super();
        this.authors = new HashSet<>();
        this.genres = new HashSet<>();
        this.price = new BigDecimal("00.00");
        this.quantity = 0;
    }
}
