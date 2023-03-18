package com.woonis.blogapi.service.search.dto;

import com.woonis.blogapi.service.kakao.dto.KaKaoSearchSort;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record BlogSearchDto(
        @NotNull(message = "검색 타입은 필수값 입니다.")
        BlogSearchType type,
        @NotBlank(message = "키워드는 필수값 입니다.")
        String keyword,
        String url,
        KaKaoSearchSort sort
) {
    @Builder
    public BlogSearchDto(BlogSearchType type,
                         String keyword,
                         String url,
                         KaKaoSearchSort sort) {
        this.type = type;
        this.keyword = keyword;
        this.url = url;
        this.sort = sort;
    }
}
