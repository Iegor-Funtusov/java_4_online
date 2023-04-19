package ua.com.alevel.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.persistence.entity.author.Author;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDto {

    private Long id;
    private String name;

    public AuthorDto(Author author) {
        this.id = author.getId();
        this.name = author.getFirstName() + " " + author.getLastName();
    }
}
