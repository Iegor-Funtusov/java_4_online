package ua.com.alevel.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.persistence.entity.publisher.Publisher;

@Getter
@Setter
@NoArgsConstructor
public class PublisherDto {

    private Long id;
    private String name;

    public PublisherDto(Publisher publisher) {
        this.id = publisher.getId();
        this.name = publisher.getName();
    }
}
