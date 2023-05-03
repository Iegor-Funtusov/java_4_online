package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.post.Reaction;
import ua.com.alevel.web.data.KeyValueData;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ReactionService {

    void deleteByPostId(Long postId);
    void like(Long rostId, Long personalId);
    void dislike(Long rostId, Long personalId);
    List<Reaction> findAllByPostIdAndLikeTrue(Long postId);
    List<Reaction> findAllByPostIdAndLikeFalse(Long postId);
    Map<String, List<KeyValueData<Date, Long>>> generateChartByPostReaction();
}
