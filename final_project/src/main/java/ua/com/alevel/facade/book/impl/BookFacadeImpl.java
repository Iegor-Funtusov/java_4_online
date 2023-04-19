package ua.com.alevel.facade.book.impl;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import ua.com.alevel.data.response.BookPDPDto;
import ua.com.alevel.data.response.BookPLPDto;
import ua.com.alevel.facade.book.BookFacade;
import ua.com.alevel.persistence.entity.book.Book;
import ua.com.alevel.service.BookService;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class BookFacadeImpl implements BookFacade {

    private final BookService bookService;

    public BookFacadeImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public BookPDPDto findById(Long id) {
        Optional<Book> optionalBook = bookService.findById(id);
        if (optionalBook.isEmpty()) {
            throw new RuntimeException("book not found");
        }
        Book book = optionalBook.get();
        return new BookPDPDto(book);
    }

    @Override
    public Collection<BookPLPDto> findAll(WebRequest webRequest) {
        Map<String, String[]> map = webRequest.getParameterMap();
        if (MapUtils.isNotEmpty(map)) {
            String[] publishers = map.get("publisher");
            if (publishers != null) {
                String publisherId = publishers[0];
                return bookService.findByPublisher(Long.parseLong(publisherId)).stream().map(BookPLPDto::new).toList();
            }
            String[] authors = map.get("author");
            if (authors != null) {
                String authorId = authors[0];
                return bookService.findByAuthors(Long.parseLong(authorId)).stream().map(BookPLPDto::new).toList();
            }
        }
        Collection<Book> books = bookService.findAll();
        return books.stream().map(BookPLPDto::new).toList();
    }
}
