package ua.com.alevel.service.impl;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import ua.com.alevel.elastic.document.PostIndex;
import ua.com.alevel.service.ElasticPostSearchService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElasticPostSearchServiceImpl implements ElasticPostSearchService {

    private static final String POST_INDEX = "postindex";

    private final ElasticsearchOperations elasticsearchOperations;

    public ElasticPostSearchServiceImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public List<String> searchPostMessage(String query) {
        QueryBuilder queryBuilder = QueryBuilders
                .wildcardQuery("message", "*" + query.toLowerCase() + "*");
        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .withPageable(PageRequest.of(0, 5))
                .build();
        SearchHits<PostIndex> searchSuggestions =
                elasticsearchOperations.search(searchQuery,
                        PostIndex.class,
                        IndexCoordinates.of(POST_INDEX));
        final List<String> suggestions = new ArrayList<>();
        searchSuggestions.getSearchHits().forEach(searchHit-> suggestions.add(searchHit.getContent().getMessage()));
        return suggestions;
    }
}
