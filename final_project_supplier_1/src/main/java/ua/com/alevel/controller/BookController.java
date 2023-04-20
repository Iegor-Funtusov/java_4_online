package ua.com.alevel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.com.alevel.dto.BookPriceQuantityDto;
import ua.com.alevel.facade.BookFacade;

import java.util.List;

@RestController
@RequestMapping(path = "/api/books")
public class BookController {

    private final BookFacade bookFacade;

    public BookController(BookFacade bookFacade) {
        this.bookFacade = bookFacade;
    }

    @GetMapping
    public ResponseEntity<List<BookPriceQuantityDto>> findAllBooks(@RequestParam(name = "isbn") String isbnList) {
        return ResponseEntity.ok(bookFacade.findBookByIsbn(isbnList));
    }
}
