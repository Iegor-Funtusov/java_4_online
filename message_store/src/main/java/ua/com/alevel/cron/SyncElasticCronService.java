package ua.com.alevel.cron;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ua.com.alevel.elastic.document.PostIndex;
import ua.com.alevel.elastic.repository.PostIndexRepository;
import ua.com.alevel.persistence.entity.post.Post;
import ua.com.alevel.persistence.repository.post.PostRepository;

import java.util.Collection;
import java.util.List;

@Service
public class SyncElasticCronService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final PostRepository postRepository;
    private final PostIndexRepository postIndexRepository;

    public SyncElasticCronService(
            ElasticsearchOperations elasticsearchOperations,
            PostRepository postRepository,
            PostIndexRepository postIndexRepository) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.postRepository = postRepository;
        this.postIndexRepository = postIndexRepository;
    }

    @Scheduled(fixedDelay = 60000)
    public void syncToPost() {
        elasticsearchOperations.indexOps(PostIndex.class).refresh();
        postIndexRepository.deleteAll();
        postIndexRepository.saveAll(prepareDataset());
    }

    private Collection<PostIndex> prepareDataset() {

//        long count = postRepository.count();
//        if (count < 1_000_000) {
//            for (int i = 0; i < count; i += 100) {
//                Page<Post> postPage = postRepository.findAll(PageRequest.of(i, 100));
//                if (postPage.getSize() != 0) {
//                    List<Post> content = postPage.getContent();
//                }
//            }
//        }

        List<Post> postList = postRepository.findAll();
        return postList
                .stream()
                .map(post -> {
                    PostIndex postIndex = new PostIndex();
                    postIndex.setMessage(post.getMessage());
                    return postIndex;
                }).toList();
    }
}
