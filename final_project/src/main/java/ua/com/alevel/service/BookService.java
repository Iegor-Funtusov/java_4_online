package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.book.Book;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> findById(Long id);
    Collection<Book> findAll();
    List<Book> findByPublisher(Long publisherId);
    List<Book> findByAuthors(Long authorId);
}
