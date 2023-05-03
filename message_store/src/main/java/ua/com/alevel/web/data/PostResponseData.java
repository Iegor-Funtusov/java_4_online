package ua.com.alevel.web.data;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.post.Post;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class PostResponseData extends PostData {

    private Map<Long, String> likes;
    private Map<Long, String> dislikes;

    public PostResponseData(Post post) {
        super(post);
        this.likes = new HashMap<>();
        this.dislikes = new HashMap<>();
    }
}
