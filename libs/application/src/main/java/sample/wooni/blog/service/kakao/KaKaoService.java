package sample.wooni.blog.service.kakao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sample.wooni.blog.service.common.Pagination;
import sample.wooni.blog.service.kakao.converter.KaKaoBlogDtoConverter;
import sample.wooni.blog.service.kakao.dto.KaKaoBlogPageDto;
import sample.wooni.blog.service.kakao.dto.KaKaoSearchSort;
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
                        .sort(Objects.nonNull(request.sort()) ? KaKaoSearchSort.findBy(request.sort()).name() : null)
                        .page(currentPage)
                        .size(countPerPage)
                        .build()
        );

        var meta = response.meta();
        return KaKaoBlogPageDto.builder()
                .documents(KaKaoBlogDtoConverter.convert(response.documents()))
                .pagination(
                        Pagination.builder()
                                .currentPage(currentPage)
                                .countPerPage(countPerPage)
                                .totalItemCount(Objects.nonNull(meta) ? meta.totalCount() : 0)
                                .totalPageCount(Objects.nonNull(meta) ? meta.pageableCount() : 0)
                                .hasNext(Objects.nonNull(meta) && !(meta.isEnd()))
                                .build()
                )
                .build();
    }
}
