package ua.com.alevel.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.persistence.entity.book.Book;
import ua.com.alevel.persistence.entity.publisher.Publisher;

@Getter
@Setter
@NoArgsConstructor
public class BookPDPDto extends BookPLPDto {

    private String description;
    private Integer pageSize;
    private Integer publicationDate;
    private PublisherDto publisher;

    public BookPDPDto(Book book) {
        super(book);
        this.description = book.getDescription();
        this.pageSize = book.getPageSize();
        this.publicationDate = book.getPublicationDate();
        initPublisher(book);
    }

    private void initPublisher(Book book) {
        Publisher publisher = book.getPublisher();
        if (publisher != null) {
            this.publisher = new PublisherDto(publisher);
        }
    }
}
