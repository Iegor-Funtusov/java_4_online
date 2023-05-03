package ua.com.alevel.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.entity.entity.Personal;
import ua.com.alevel.persistence.entity.post.Post;
import ua.com.alevel.persistence.entity.post.Reaction;
import ua.com.alevel.persistence.repository.post.PostRepository;
import ua.com.alevel.persistence.repository.post.ReactionRepository;
import ua.com.alevel.persistence.repository.user.PersonalRepository;
import ua.com.alevel.service.ReactionService;
import ua.com.alevel.util.PostUtil;
import ua.com.alevel.util.SecurityUtil;
import ua.com.alevel.web.data.KeyValueData;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReactionServiceImpl implements ReactionService {

    private final ReactionRepository reactionRepository;
    private final PersonalRepository personalRepository;
    private final PostRepository postRepository;

    public ReactionServiceImpl(
            ReactionRepository reactionRepository,
            PersonalRepository personalRepository,
            PostRepository postRepository) {
        this.reactionRepository = reactionRepository;
        this.personalRepository = personalRepository;
        this.postRepository = postRepository;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_PERSONAL')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void deleteByPostId(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("post not found"));
        List<Reaction> reactions = reactionRepository.findAllByPost(post);
        if (CollectionUtils.isNotEmpty(reactions)) {
            reactionRepository.deleteAll(reactions);
        }
    }

    @Override
    @PreAuthorize("hasRole('ROLE_PERSONAL')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void like(Long postId, Long personalId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("post not found"));
        Personal personal = personalRepository.findById(personalId).orElseThrow(() -> new RuntimeException("personal not found"));
        reactionProcess(post, personal, true);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_PERSONAL')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void dislike(Long postId, Long personalId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("post not found"));
        Personal personal = personalRepository.findById(personalId).orElseThrow(() -> new RuntimeException("personal not found"));
        reactionProcess(post, personal, false);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_PERSONAL')")
    @Transactional(readOnly = true)
    public List<Reaction> findAllByPostIdAndLikeTrue(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("post not found"));
        return reactionRepository.findAllByPostAndLikeTrue(post);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_PERSONAL')")
    @Transactional(readOnly = true)
    public List<Reaction> findAllByPostIdAndLikeFalse(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("post not found"));
        return reactionRepository.findAllByPostAndLikeFalse(post);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_PERSONAL')")
    @Transactional(readOnly = true)
    public Map<String, List<KeyValueData<Date, Long>>> generateChartByPostReaction() {
        List<Long> postIds = generateAllPostIdListByPersonal();
        if (CollectionUtils.isEmpty(postIds)) {
            return Collections.emptyMap();
        }
        Map<String, List<KeyValueData<Date, Long>>> chartDataMap = new HashMap<>();
        chartDataMap.put(PostUtil.POST_ALL, reactionRepository.generateAllPostWithoutReaction(postIds));
        chartDataMap.put(PostUtil.LIKE_ALL, reactionRepository.generateAllPostByReaction(postIds, true));
        chartDataMap.put(PostUtil.DISLIKE_ALL, reactionRepository.generateAllPostByReaction(postIds, false));
        return chartDataMap;
    }

    private void reactionProcess(Post post, Personal personal, boolean status) {
        Reaction reaction = reactionRepository.findByPostAndPersonal(post, personal);
        if (reaction == null) {
            reaction = new Reaction();
            reaction.setPersonal(personal);
            reaction.setPost(post);
        }
        reaction.setLike(status);
        reactionRepository.save(reaction);
    }

    private List<Long> generateAllPostIdListByPersonal() {
        Personal personal = (Personal) personalRepository.findByEmail(SecurityUtil.getUsername()).orElseThrow(() -> new RuntimeException("Personal not found"));
        List<Post> posts = postRepository.findAllByPersonal(personal);
        if (CollectionUtils.isEmpty(posts)) {
            return Collections.emptyList();
        }
        return posts.stream().map(Post::getId).collect(Collectors.toList());
    }
}
