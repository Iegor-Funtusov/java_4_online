package ua.com.alevel.scheduler;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ua.com.alevel.persistence.entity.book.Book;
import ua.com.alevel.persistence.repository.book.BookRepository;
import ua.com.alevel.scheduler.dto.BookPriceQuantityDto;

import java.util.Arrays;
import java.util.List;

@Service
public class SupplierScheduler {

    @Value("${supplier1.auth.token}")
    private String supplier1AuthToken;

    @Value("${supplier1.url}")
    private String supplier1Url;

    private final BookRepository bookRepository;

    public SupplierScheduler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Scheduled(cron = "0 * * ? * *")
    public void callToSupplier() {
        List<String> isbnList = bookRepository.findAllIsbnWhereQuantityIsZero();
        if (CollectionUtils.isNotEmpty(isbnList)) {
            StringBuilder queryBuilder = new StringBuilder("/api/books?isbn=");
            for (String isbn : isbnList) {
                queryBuilder.append(isbn);
                queryBuilder.append(",");
            }
            final String url = supplier1Url + queryBuilder;
            final RestTemplate restTemplate = new RestTemplate();
            final HttpHeaders headers = new HttpHeaders();
            headers.add("Auth-token", supplier1AuthToken);
            HttpEntity<BookPriceQuantityDto[]> entity = new HttpEntity<>(headers);
            ResponseEntity<BookPriceQuantityDto[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    BookPriceQuantityDto[].class);
            if (response.getStatusCodeValue() == 200) {
                BookPriceQuantityDto[] body = response.getBody();
                if (ArrayUtils.isNotEmpty(body)) {
                    List<BookPriceQuantityDto> dtoList = Arrays.asList(body);
                    List<Book> books = bookRepository.findAllByIsbnIn(isbnList);
                    for (Book book : books) {
                        BookPriceQuantityDto dto = dtoList
                                .stream()
                                .filter(bookPriceQuantityDto -> bookPriceQuantityDto.getIsbn().equals(book.getIsbn()))
                                .findFirst()
                                .get();
                        book.setPrice(dto.getPrice());
                        book.setQuantity(dto.getQuantity());
                    }
                    bookRepository.saveAll(books);
                }
            }
        }
    }
}
