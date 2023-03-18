package com.woonis.blogapi.service.kakao;

import com.woonis.blogapi.client.kakao.KaKaoClient;
import com.woonis.blogapi.client.kakao.dto.request.KaKaoSearchRequest;
import com.woonis.blogapi.service.common.Pagination;
import com.woonis.blogapi.service.kakao.converter.KaKaoBlogDtoConverter;
import com.woonis.blogapi.service.kakao.dto.KaKaoBlogPageDto;
import com.woonis.blogapi.service.search.dto.BlogSearchDto;
import com.woonis.blogapi.service.search.dto.BlogSearchPageDto;
import com.woonis.blogapi.service.search.dto.BlogSearchType;
import com.woonis.blogapi.service.search.external.ExternalBlogSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class KaKaoService implements ExternalBlogSearchService {

    private final KaKaoClient client;

    public KaKaoService(KaKaoClient client) {
        this.client = client;
    }

    @Override
    public boolean isTarget(BlogSearchType type) {
        return BlogSearchType.KAKAO == type;
    }

    @Override
    public BlogSearchPageDto search(BlogSearchDto request, int currentPage, int countPerPage) {
        var response = client.searchBlog(
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
