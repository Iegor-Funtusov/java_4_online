package ua.com.alevel.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.author.Author;
import ua.com.alevel.persistence.entity.book.Book;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BookPLPDto {

    private Long id;
    private String bookName;
    private String imageUrl;
    private List<AuthorDto> authors;
    private String price;
    private Boolean visible;

    public BookPLPDto(Book book) {
        this.id = book.getId();
        this.bookName = book.getBookName();
        this.imageUrl = book.getImageUrl();
        this.price = book.getPrice().toString();
        this.visible = book.getVisible();
        initAuthors(book);
    }

    private void initAuthors(Book book) {
        Set<Author> authors = book.getAuthors();
        if (CollectionUtils.isNotEmpty(authors)) {
            this.authors = authors.stream().map(AuthorDto::new).toList();
        }
    }
}
