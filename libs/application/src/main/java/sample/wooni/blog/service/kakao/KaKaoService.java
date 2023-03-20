package sample.wooni.blog.service.kakao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sample.wooni.blog.service.common.Pagination;
import sample.wooni.blog.service.kakao.converter.KaKaoBlogDtoConverter;
import sample.wooni.blog.service.kakao.dto.KaKaoBlogPageDto;
import sample.wooni.blog.service.output.kakao.ExternalKaKaoBlogOutput;
import sample.wooni.blog.service.output.kakao.dto.request.KaKaoSearchRequest;
import sample.wooni.blog.service.search.dto.BlogSearchDto;
import sample.wooni.blog.service.search.dto.BlogSearchPageDto;
import sample.wooni.blog.service.search.dto.BlogSearchType;
import sample.wooni.blog.service.search.external.ExternalBlogSearchService;

import java.util.Objects;

@Slf4j
@Component
public class KaKaoService implements ExternalBlogSearchService {

    private final ExternalKaKaoBlogOutput externalKaKaoBlogOutput;

    public KaKaoService(ExternalKaKaoBlogOutput externalKaKaoBlogOutput) {
        this.externalKaKaoBlogOutput = externalKaKaoBlogOutput;
    }

    @Override
    public boolean isTarget(BlogSearchType type) {
        return BlogSearchType.KAKAO == type;
    }

    @Override
    public BlogSearchPageDto search(BlogSearchDto request, int currentPage, int countPerPage) {
        var response = externalKaKaoBlogOutput.searchBlog(
                KaKaoSearchRequest.builder()
                        .keyword(request.keyword())
                        .url(request.url())
                        .sort(Objects.nonNull(request.sort()) ? request.sort().name() : "")
                        .page(currentPage)
                        .size(countPerPage)
                        .build()
        );

        return KaKaoBlogPageDto.builder()
                .documents(KaKaoBlogDtoConverter.convert(response.documents()))
                .pagination(
                        Pagination.builder()
                                .currentPage(currentPage)
                                .countPerPage(countPerPage)
                                .kaKaoMeta(response.meta())
                                .build()
                )
                .build();
    }
}