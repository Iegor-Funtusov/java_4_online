package ua.com.alevel.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.book.BookFacade;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookFacade bookFacade;

    public BookController(BookFacade bookFacade) {
        this.bookFacade = bookFacade;
    }

    @GetMapping
    public String plp(Model model, WebRequest webRequest) {
        model.addAttribute("bookList", bookFacade.findAll(webRequest));
        return "pages/open/book_plp";
    }

    @GetMapping("/{id}")
    public String pdp(Model model, @PathVariable Long id) {
        model.addAttribute("book", bookFacade.findById(id));
        return "pages/open/book_pdp";
    }
}
