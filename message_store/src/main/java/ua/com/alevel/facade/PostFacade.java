package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import ua.com.alevel.web.data.PageData;
import ua.com.alevel.web.data.PersonalDashboardChartData;
import ua.com.alevel.web.data.PostData;
import ua.com.alevel.web.data.PostResponseData;

import java.util.List;

public interface PostFacade {

    void create(PostData data);
    void update(PostData data, Long id);
    void delete(Long id);
    PageData<PostData> findAll(WebRequest request);
    PostResponseData findById(Long id);
    void like(Long id);
    void dislike(Long id);
    void uploadFile(MultipartFile file, Integer postId);
    PersonalDashboardChartData generatePersonalDashboardChartData();
    List<String> searchPostMessage(String query);
}
