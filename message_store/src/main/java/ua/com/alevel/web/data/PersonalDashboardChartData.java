package ua.com.alevel.web.data;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PersonalDashboardChartData {

    private List<Date> labels;
    private List<Long> allPost;
    private List<Long> likePost;
    private List<Long> dislikePost;

    public PersonalDashboardChartData() {
        this.labels = Collections.emptyList();
        this.allPost = Collections.emptyList();
        this.likePost = Collections.emptyList();
        this.dislikePost = Collections.emptyList();
    }
}
