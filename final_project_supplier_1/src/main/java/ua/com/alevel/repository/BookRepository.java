package ua.com.alevel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.dto.BookPriceQuantityDto;
import ua.com.alevel.entity.book.Book;

import java.util.List;

@Repository
public interface BookRepository extends BaseRepository<Book> {

    @Query("select new ua.com.alevel.dto.BookPriceQuantityDto(b.isbn, b.price, b.quantity) from Book b where b.isbn in :isbnList")
    List<BookPriceQuantityDto> findBookByIsbn(@Param("isbnList") List<String> isbnList);
}
