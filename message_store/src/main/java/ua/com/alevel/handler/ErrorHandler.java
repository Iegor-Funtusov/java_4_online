package ua.com.alevel.handler;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String serverError(final Throwable throwable, final Model model) {
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("showMessage", true);
        return "error";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(final Exception throwable, final Model model) {
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("showMessage", true);
        return "error";
    }
}
