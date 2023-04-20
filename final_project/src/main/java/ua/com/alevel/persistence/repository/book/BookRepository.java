package ua.com.alevel.persistence.repository.book;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.author.Author;
import ua.com.alevel.persistence.entity.book.Book;
import ua.com.alevel.persistence.entity.publisher.Publisher;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends BaseRepository<Book> {

    List<Book> findByPublisher(Publisher publisher);
    List<Book> findByBookNameContaining(String bookName);
    List<Book> findAllByIsbnIn(List<String> isbn);

    @Query("from Book b join b.authors ba where ba.id = :authorId")
    List<Book> findByAuthor(@Param("authorId") Long authorId);

    @Query("select b.isbn from Book b where b.quantity = 0")
    List<String> findAllIsbnWhereQuantityIsZero();
}
