package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.BookPriceQuantityDto;
import ua.com.alevel.facade.BookFacade;
import ua.com.alevel.service.BookService;

import java.util.List;

@Service
public class BookFacadeImpl implements BookFacade {

    private final BookService bookService;

    public BookFacadeImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public List<BookPriceQuantityDto> findBookByIsbn(String isbnList) {
        List<String> isbns = List.of(isbnList.split(","));
        return bookService.findBookByIsbn(isbns);
    }
}
