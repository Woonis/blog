package sample.wooni.blog.service.kakao.dto;

import sample.wooni.blog.service.common.Pagination;
import sample.wooni.blog.service.search.dto.BlogSearchPageDto;
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
