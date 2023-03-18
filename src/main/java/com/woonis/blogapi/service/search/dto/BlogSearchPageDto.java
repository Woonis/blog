package com.woonis.blogapi.service.search.dto;

import com.woonis.blogapi.service.common.Pagination;
import lombok.Getter;

@Getter
public class BlogSearchPageDto {
    private final Pagination pagination;

    public BlogSearchPageDto(Pagination pagination) {
        this.pagination = pagination;
    }
}
