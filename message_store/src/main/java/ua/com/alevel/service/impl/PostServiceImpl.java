package ua.com.alevel.service.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.entity.post.Post;
import ua.com.alevel.persistence.repository.post.PostRepository;
import ua.com.alevel.persistence.repository.user.PersonalRepository;
import ua.com.alevel.service.PostService;
import ua.com.alevel.service.ReactionService;
import ua.com.alevel.util.SecurityUtil;
import ua.com.alevel.web.data.PersistenceRequestData;

import java.util.Map;

import static ua.com.alevel.util.WebRequestUtil.SEARCH_MESSAGE_PARAM;

@Service
public class PostServiceImpl implements PostService {

    private final PersonalRepository personalRepository;
    private final PostRepository postRepository;
    private final ReactionService reactionService;

    public PostServiceImpl(
            PersonalRepository personalRepository,
            PostRepository postRepository,
            ReactionService reactionService) {
        this.personalRepository = personalRepository;
        this.postRepository = postRepository;
        this.reactionService = reactionService;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_PERSONAL')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void create(Post post) {
        Personal personal = (Personal) personalRepository
                .findByEmail(SecurityUtil.getUsername())
                .orElseThrow(() -> new RuntimeException("Personal not found"));
        post.setPersonal(personal);
        postRepository.save(post);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_PERSONAL')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void update(Post post) {
        Personal personal = (Personal) personalRepository
                .findByEmail(SecurityUtil.getUsername())
                .orElseThrow(() -> new RuntimeException("Personal not found"));
        Post current = postRepository.findById(post.getId()).orElse(null);
        validPost(post, personal.getId());
        current.setTitle(post.getTitle());
        current.setMessage(post.getMessage());
        current.setComment(post.getComment());
        postRepository.save(current);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_PERSONAL')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void delete(Long id) {
        Personal personal = (Personal) personalRepository
                .findByEmail(SecurityUtil.getUsername())
                .orElseThrow(() -> new RuntimeException("Personal not found"));
        Post post = postRepository.findById(id).orElse(null);
        validPost(post, personal.getId());
        postRepository.delete(post);
        reactionService.deleteByPostId(post.getId());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_PERSONAL')")
    @Transactional(readOnly = true)
    public Post findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        existPost(post);
        return post;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_PERSONAL')")
    @Transactional(readOnly = true)
    public Page<Post> findAll(PersistenceRequestData data) {
        Personal personal = (Personal) personalRepository
                .findByEmail(SecurityUtil.getUsername())
                .orElseThrow(() -> new RuntimeException("Personal not found"));
        Sort sort = data.getOrder().equals("desc") ? Sort.by(Sort.Order.desc(data.getSort())) : Sort.by(Sort.Order.asc(data.getSort()));
        if (data.isOwner()) {
            return postRepository.findAllByPersonal(personal, PageRequest.of(data.getPage() - 1, data.getSize(), sort));
        } else {
            Map<String, Object> parameters = data.getParameters();
            if (MapUtils.isNotEmpty(parameters)) {
                String query = (String) parameters.get(SEARCH_MESSAGE_PARAM);
                if (StringUtils.isNotBlank(query)) {
                    return postRepository.findAllByPersonalIsNotAndMessageContainingIgnoreCase(personal, query, PageRequest.of(data.getPage() - 1, data.getSize(), sort));
                }
            }
            return postRepository.findAllByPersonalIsNot(personal, PageRequest.of(data.getPage() - 1, data.getSize(), sort));
        }
    }

    @Override
    @PreAuthorize("hasRole('ROLE_PERSONAL')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void like(Long id) {
        reactionProcess(id, true);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_PERSONAL')")
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void dislike(Long id) {
        reactionProcess(id, false);
    }

    private void validPost(Post post, Long personalId) {
        existPost(post);
        if (!post.getPersonal().getId().equals(personalId)) {
            throw new RuntimeException("you are not a owner");
        }
        if (StringUtils.isBlank(post.getMessage()) || StringUtils.isBlank(post.getTitle())) {
            throw new RuntimeException("message or title can not be empty");
        }
    }

    private void existPost(Post post) {
        if (post == null) {
            throw new RuntimeException("post not found");
        }
    }

    private void reactionProcess(Long postId, boolean status) {
        Personal personal = (Personal) personalRepository
                .findByEmail(SecurityUtil.getUsername())
                .orElseThrow(() -> new RuntimeException("Personal not found"));
        Post post = postRepository.findById(postId).orElse(null);
        hasReactionToPost(post, personal.getId());
        if (status) {
            reactionService.like(post.getId(), personal.getId());
        } else {
            reactionService.dislike(post.getId(), personal.getId());
        }
    }

    private void hasReactionToPost(Post post, Long personalId) {
        existPost(post);
        if (post.getPersonal().getId().equals(personalId)) {
            throw new RuntimeException("you do not have a reaction to this post");
        }
    }
}
