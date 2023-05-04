package ua.com.alevel.service;

import java.util.List;

public interface ElasticPostSearchService {

    List<String> searchPostMessage(String query);
}
