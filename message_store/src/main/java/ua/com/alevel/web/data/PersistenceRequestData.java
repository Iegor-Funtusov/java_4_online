package ua.com.alevel.web.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.util.WebRequestUtil;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class PersistenceRequestData {

    private int page;
    private int size;
    private String sort;
    private String order;
    private boolean owner;
    private Map<String, Object> parameters;

    public PersistenceRequestData(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        this.page = pageAndSizeData.getPage();
        this.size = pageAndSizeData.getSize();
        this.sort = sortData.getSort();
        this.order = sortData.getOrder();
        this.owner = WebRequestUtil.getOwner(request);
        this.parameters = WebRequestUtil.generateParameters(request);
    }
}
