package ua.com.alevel.scheduler.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookPriceQuantityDto {

    private String isbn;
    private BigDecimal price;
    private Integer quantity;
}
