package sample.wooni.blog.service.search.dto;

import lombok.Getter;
import sample.wooni.blog.service.common.Pagination;

@Getter
public class BlogSearchPageDto {
    private final Pagination pagination;

    public BlogSearchPageDto(Pagination pagination) {
        this.pagination = pagination;
    }
}
