package ua.com.alevel.facade.book;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.data.response.BookPDPDto;
import ua.com.alevel.data.response.BookPLPDto;

import java.util.Collection;

public interface BookFacade {

    BookPDPDto findById(Long id);
    Collection<BookPLPDto> findAll(WebRequest webRequest);
}
