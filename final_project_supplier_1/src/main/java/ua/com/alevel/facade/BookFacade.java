package ua.com.alevel.facade;

import ua.com.alevel.dto.BookPriceQuantityDto;

import java.util.List;

public interface BookFacade {

    List<BookPriceQuantityDto> findBookByIsbn(String isbnList);
}
