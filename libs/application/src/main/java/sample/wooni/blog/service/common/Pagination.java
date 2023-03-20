package sample.wooni.blog.service.common;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Pagination {
    private final long totalPageCount;

    private final long totalItemCount;
    private final int currentPage;
    private final int countPerPage;
    private final boolean hasNext;

    @Builder
    public Pagination(int totalPageCount,
                      int totalItemCount,
                      boolean hasNext,
                      int currentPage,
                      int countPerPage) {
        this.currentPage = currentPage > 0 ? currentPage : 1;
        this.countPerPage = countPerPage > 0 ? countPerPage : 10;

        this.totalPageCount = totalPageCount;
        this.totalItemCount = totalItemCount;
        this.hasNext = hasNext;
    }
}
