package com.woonis.blogapi.service.kakao.dto;

import com.woonis.blogapi.service.common.Pagination;
import com.woonis.blogapi.service.search.dto.BlogSearchPageDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class KaKaoBlogPageDto extends BlogSearchPageDto {
    private final List<KaKaoBlogDetailDto> documents;

    @Builder
    public KaKaoBlogPageDto(List<KaKaoBlogDetailDto> documents,
                            Pagination pagination) {
        super(pagination);
        this.documents = documents;
    }
}
