package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.dto.BookPriceQuantityDto;
import ua.com.alevel.repository.BookRepository;
import ua.com.alevel.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookPriceQuantityDto> findBookByIsbn(List<String> isbnList) {
        return bookRepository.findBookByIsbn(isbnList);
    }
}
