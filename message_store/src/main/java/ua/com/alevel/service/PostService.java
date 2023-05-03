package ua.com.alevel.service;

import org.springframework.web.multipart.MultipartFile;
import ua.com.alevel.persistence.entity.post.Post;

public interface PostService extends CrudService<Post> {

    void like(Long id);
    void dislike(Long id);
//    void uploadFile(MultipartFile file, Integer postId);
}
