package ua.com.alevel.facade.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import ua.com.alevel.facade.PostFacade;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.entity.post.Post;
import ua.com.alevel.persistence.entity.post.Reaction;
import ua.com.alevel.service.ElasticPostSearchService;
import ua.com.alevel.service.PersonalService;
import ua.com.alevel.service.PostService;
import ua.com.alevel.service.ReactionService;
import ua.com.alevel.util.PostUtil;
import ua.com.alevel.web.data.KeyValueData;
import ua.com.alevel.web.data.PageData;
import ua.com.alevel.web.data.PersistenceRequestData;
import ua.com.alevel.web.data.PersonalDashboardChartData;
import ua.com.alevel.web.data.PostData;
import ua.com.alevel.web.data.PostResponseData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PostFacadeImpl implements PostFacade {

    private final PostService postService;
    private final ReactionService reactionService;
    private final PersonalService personalService;
    private final ElasticPostSearchService elasticPostSearchService;

    public PostFacadeImpl(
            PostService postService,
            ReactionService reactionService,
            PersonalService personalService,
            ElasticPostSearchService elasticPostSearchService) {
        this.postService = postService;
        this.reactionService = reactionService;
        this.personalService = personalService;
        this.elasticPostSearchService = elasticPostSearchService;
    }

    @Override
    public void create(PostData data) {
        Post post = new Post();
        post.setMessage(data.getMessage());
        post.setTitle(data.getTitle());
        post.setComment(data.getComment());
        postService.create(post);
    }

    @Override
    public void update(PostData data, Long id) {
        Post post = postService.findById(id);
        post.setMessage(data.getMessage());
        post.setTitle(data.getTitle());
        post.setComment(data.getComment());
        postService.update(post);
    }

    @Override
    public void delete(Long id) {
        postService.delete(id);
    }

    @Override
    public PageData<PostData> findAll(WebRequest request) {
        PersistenceRequestData persistenceRequestData = new PersistenceRequestData(request);
        Page<Post> page = postService.findAll(persistenceRequestData);
        PageData<PostData> data = new PageData<>();
        data.setCurrentPage(page.getNumber());
        data.setPageSize(page.getNumber());
        data.setTotalElements(page.getTotalPages());
        data.setTotalPages(page.getTotalPages());
        if (CollectionUtils.isNotEmpty(page.getContent())) {
            List<PostData> list = page.getContent().stream().map(PostData::new).collect(Collectors.toList());
            data.setItems(list);
        }
        return data;
    }

    @Override
    public PostResponseData findById(Long id) {
        Post post = postService.findById(id);
        List<Reaction> likeReactionList = reactionService.findAllByPostIdAndLikeTrue(post.getId());
        List<Reaction> dislikeReactionList = reactionService.findAllByPostIdAndLikeFalse(post.getId());
        PostResponseData data = new PostResponseData(post);
        if (CollectionUtils.isNotEmpty(likeReactionList)) {
//            List<Long> ids = likeReactionList.stream().map(Reaction::getPersonal).map(Personal::getId).collect(Collectors.toList());
            List<Long> ids = new ArrayList<>();
            for (Reaction reaction : likeReactionList) {
                Personal personal = reaction.getPersonal();
                if (personal != null) {
                    ids.add(personal.getId());
                }
            }
            generatePostResponseData(data, ids, null);
        }
        if (CollectionUtils.isNotEmpty(dislikeReactionList)) {
//            List<Long> ids = dislikeReactionList.stream().map(Reaction::getPersonal).map(Personal::getId).collect(Collectors.toList());
            List<Long> ids = new ArrayList<>();
            for (Reaction reaction : dislikeReactionList) {
                Personal personal = reaction.getPersonal();
                if (personal != null) {
                    ids.add(personal.getId());
                }
            }
            generatePostResponseData(data, null, ids);
        }
        return data;
    }

    @Override
    public void like(Long id) {
        postService.like(id);
    }

    @Override
    public void dislike(Long id) {
        postService.dislike(id);
    }

    @Override
    public void uploadFile(MultipartFile file, Integer postId) {

    }

    @Override
    public PersonalDashboardChartData generatePersonalDashboardChartData() {
        PersonalDashboardChartData data = new PersonalDashboardChartData();
        Map<String, List<KeyValueData<Date, Long>>> chartDataMap = reactionService.generateChartByPostReaction();
        if (MapUtils.isEmpty(chartDataMap)) {
            return data;
        }

        List<KeyValueData<Date, Long>> allPostData = chartDataMap.get(PostUtil.POST_ALL);
        List<KeyValueData<Date, Long>> likePostData = chartDataMap.get(PostUtil.LIKE_ALL);
        List<KeyValueData<Date, Long>> dislikePostData = chartDataMap.get(PostUtil.DISLIKE_ALL);

        List<Long> allPost = allPostData.stream().map(KeyValueData::getValue).collect(Collectors.toUnmodifiableList());
        List<Long> likePost = new ArrayList<>();
        List<Long> dislikePost = new ArrayList<>();

        List<Date> dates = allPostData.stream().map(KeyValueData::getKey).collect(Collectors.toUnmodifiableList());

        KeyValueData<Date, Long> keyValue;
        for (Date reactionDate : dates) {
            keyValue = likePostData.stream().filter(keyValueData -> keyValueData.getKey().getTime() == reactionDate.getTime()).findAny().orElse(null);
            if (keyValue == null) {
                likePost.add(0L);
            } else {
                likePost.add(keyValue.getValue());
            }
            keyValue = dislikePostData.stream().filter(keyValueData -> keyValueData.getKey().getTime() == reactionDate.getTime()).findAny().orElse(null);
            if (keyValue == null) {
                dislikePost.add(0L);
            } else {
                dislikePost.add(keyValue.getValue());
            }
        }

        data.setLabels(dates);
        data.setAllPost(allPost);
        data.setLikePost(likePost);
        data.setDislikePost(dislikePost);

        return data;
    }

    @Override
    public List<String> searchPostMessage(String query) {
        return elasticPostSearchService.searchPostMessage(query);
    }

    private void generatePostResponseData(PostResponseData data, List<Long> likeIds, List<Long> dislikeIds) {
        List<Personal> personals;
        Map<Long, String> map;
        if (likeIds != null) {
            personals = personalService.findAllByListId(likeIds);
            map = personals.stream().collect(Collectors.toMap(Personal::getId, Personal::getFullName));
            data.setLikes(map);
        }
        if (dislikeIds != null) {
            personals = personalService.findAllByListId(dislikeIds);
            map = personals.stream().collect(Collectors.toMap(Personal::getId, Personal::getFullName));
            data.setDislikes(map);
        }
    }
}
