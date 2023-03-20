package sample.wooni.blog.service.common;

import lombok.Builder;
import lombok.Getter;
import sample.wooni.blog.service.output.kakao.dto.response.KaKaoMeta;

import java.util.Objects;

@Getter
public class Pagination {
    private final long totalPageCount;

    private final long totalItemCount;
    private final int currentPage;
    private final int countPerPage;
    private final boolean hasNext;


    // TODO KaKaoMeta 디펜던시 제거
    @Builder
    public Pagination(KaKaoMeta kaKaoMeta,
                      int currentPage,
                      int countPerPage) {
        this.currentPage = currentPage > 0 ? currentPage : 1;
        this.countPerPage = countPerPage > 0 ? countPerPage : 10;

        this.totalPageCount = Objects.nonNull(kaKaoMeta) ? kaKaoMeta.pageableCount(): 0;
        this.totalItemCount = Objects.nonNull(kaKaoMeta) ? kaKaoMeta.totalCount() : 0;
        this.hasNext = Objects.nonNull(kaKaoMeta) && !kaKaoMeta.isEnd();
    }
}
