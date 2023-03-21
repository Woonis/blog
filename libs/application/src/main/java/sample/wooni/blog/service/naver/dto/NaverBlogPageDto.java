package sample.wooni.blog.service.naver.dto;

import lombok.Builder;
import lombok.Getter;
import sample.wooni.blog.service.common.Pagination;
import sample.wooni.blog.service.search.dto.BlogSearchPageDto;

import java.util.List;

@Getter
public class NaverBlogPageDto extends BlogSearchPageDto {
    private final List<NaverBlogDetailDto> documents;

    @Builder
    public NaverBlogPageDto(Pagination pagination,
                            List<NaverBlogDetailDto> documents) {
        super(pagination);
        this.documents = documents;
    }
}
