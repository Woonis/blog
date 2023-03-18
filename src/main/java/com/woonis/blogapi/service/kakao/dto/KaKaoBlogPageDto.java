package com.woonis.blogapi.service.kakao.dto;

import com.woonis.blogapi.service.common.Pagination;
import lombok.Builder;

import java.util.List;

public record KaKaoBlogPageDto(
        List<KaKaoBlogDetailDto> documents,
        Pagination pagination
) {

    @Builder
    public KaKaoBlogPageDto{
    }
}
