package ua.com.alevel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookPriceQuantityDto {

    private String isbn;
    private BigDecimal price;
    private Integer quantity;

//    public BookPriceQuantityDto(String isbn, BigDecimal price, Integer quantity) {
//        this.isbn = isbn;
//        this.price = price;
//        this.quantity = quantity;
//    }
}
