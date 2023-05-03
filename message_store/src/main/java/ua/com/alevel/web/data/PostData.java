package ua.com.alevel.web.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.persistence.entity.post.Post;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostData {

    private Long id;
    private String title;
    private String message;
    private String comment;
    private Date created;

    public PostData(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.comment = post.getComment();
        this.message = post.getMessage();
        this.created = post.getCreated();
    }
}
