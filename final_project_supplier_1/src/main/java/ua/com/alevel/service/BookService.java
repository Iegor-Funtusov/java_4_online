package ua.com.alevel.service;

import ua.com.alevel.dto.BookPriceQuantityDto;

import java.util.List;

public interface BookService {

    List<BookPriceQuantityDto> findBookByIsbn(List<String> isbnList);
}
