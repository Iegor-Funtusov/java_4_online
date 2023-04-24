package ua.com.alevel.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ua.com.alevel.persistence.entity.book.Book;
import ua.com.alevel.persistence.entity.publisher.Publisher;
import ua.com.alevel.persistence.repository.book.BookRepository;
import ua.com.alevel.persistence.repository.book.PublisherRepository;
import ua.com.alevel.service.BookService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

//    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BookServiceImpl(BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Optional<Book> findById(Long id) {
        log.info("some message");
        return bookRepository.findById(id);
    }

    @Override
    public Collection<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByPublisher(Long publisherId) {
        Publisher publisher = publisherRepository.findById(publisherId).get();
        return bookRepository.findByPublisher(publisher);
    }

    @Override
    public List<Book> findByAuthors(Long authorId) {
        return bookRepository.findByAuthor(authorId);
    }
}
