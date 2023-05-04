package ua.com.alevel.persistence.repository.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.entity.post.Post;
import ua.com.alevel.persistence.repository.AbstractRepository;

import java.util.List;

@Repository
public interface PostRepository extends AbstractRepository<Post> {

    List<Post> findAllByPersonal(Personal personal);
    Page<Post> findAllByPersonal(Personal personal, Pageable pageable);
    Page<Post> findAllByPersonalIsNot(Personal personal, Pageable pageable);
    Page<Post> findAllByPersonalIsNotAndMessageContainingIgnoreCase(Personal personal, String message, Pageable pageable);
}
