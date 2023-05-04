package ua.com.alevel.persistence.repository.post;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.entity.post.Post;
import ua.com.alevel.persistence.entity.post.Reaction;
import ua.com.alevel.persistence.repository.AbstractRepository;
import ua.com.alevel.web.data.KeyValueData;

import java.util.Date;
import java.util.List;

@Repository
public interface ReactionRepository extends AbstractRepository<Reaction> {

    List<Reaction> findAllByPost(Post post);
    List<Reaction> findAllByPostAndLikeTrue(Post post);
    List<Reaction> findAllByPostAndLikeFalse(Post post);
    Reaction findByPostAndPersonal(Post post, Personal personal);

    @Query("select new ua.com.alevel.web.data.KeyValueData(reaction.created, count(reaction.like)) " +
            "from Reaction as reaction where reaction.post.id in :postIds " +
            "group by reaction.created order by reaction.created asc ")
    List<KeyValueData<Date, Long>> generateAllPostWithoutReaction(@Param("postIds") List<Long> postIds);

    @Query("select new ua.com.alevel.web.data.KeyValueData(reaction.created, count(reaction.like)) " +
            "from Reaction as reaction where reaction.post.id in :postIds and reaction.like = :react " +
            "group by reaction.created order by reaction.created asc ")
    List<KeyValueData<Date, Long>> generateAllPostByReaction(@Param("postIds") List<Long> postIds, @Param("react") Boolean react);
}
