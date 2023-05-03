package ua.com.alevel.web.data;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

import static ua.com.alevel.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;
import static ua.com.alevel.util.WebRequestUtil.DEFAULT_PAGE_PARAM_VALUE;
import static ua.com.alevel.util.WebRequestUtil.DEFAULT_SIZE_PARAM_VALUE;
import static ua.com.alevel.util.WebRequestUtil.DEFAULT_SORT_PARAM_VALUE;

@Getter
@Setter
public class PageData<T> {

    private int currentPage;
    private int pageSize;
    private long totalElements;
    private long totalPages;
    private boolean showFirst;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showLast;
    private int[] pageSizeList;
    private long currentShowFromEntries;
    private long currentShowToEntries;
    private String sort;
    private String order;
    private List<T> items;

    public PageData() {
        this.currentPage = DEFAULT_PAGE_PARAM_VALUE;
        this.pageSize = DEFAULT_SIZE_PARAM_VALUE;
        this.totalElements = 0;
        this.showFirst = true;
        this.showPrevious = false;
        this.showNext = false;
        this.showLast = false;
        this.pageSizeList = new int[] { 10, 25, 50, 100 };
        this.sort = DEFAULT_SORT_PARAM_VALUE;
        this.order = DEFAULT_ORDER_PARAM_VALUE;
        this.items = Collections.emptyList();
    }

    public void initPaginationState(int page) {
        this.showFirst = page != 1;
        this.showLast = page != totalPages;
        this.showNext = page != totalPages;
        this.showPrevious = page - 1 != 0;
    }
}
