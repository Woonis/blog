package com.woonis.blogapi.service.kakao;

import com.woonis.blogapi.client.kakao.KaKaoClient;
import com.woonis.blogapi.client.kakao.dto.request.KaKaoSearchRequest;
import com.woonis.blogapi.client.kakao.dto.response.KaKaoBlogResponse;
import com.woonis.blogapi.converter.KaKaoBlogDtoConverter;
import com.woonis.blogapi.service.common.Pagination;
import com.woonis.blogapi.service.kakao.dto.KaKaoBlogPageDto;
import com.woonis.blogapi.service.kakao.dto.KaKaoBlogSearchDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class KaKaoService {

    private final KaKaoClient client;

    public KaKaoService(KaKaoClient client) {
        this.client = client;
    }

    public KaKaoBlogPageDto search(KaKaoBlogSearchDto request, int page, int countPerPage) {
        KaKaoBlogResponse response = client.searchBlog(
                KaKaoSearchRequest.builder()
                        .keyword(request.keyword())
                        .url(request.url())
                        .sort(Objects.nonNull(request.sort()) ? request.sort().name() : "")
                        .page(page)
                        .size(countPerPage)
                        .build()
        );

        return KaKaoBlogPageDto.builder()
                .documents(KaKaoBlogDtoConverter.convert(response.documents()))
                .pagination(
                        Pagination.builder()
                                .currentPage(page)
                                .countPerPage(countPerPage)
                                .kaKaoMeta(response.meta())
                                .build()
                )
                .build();
    }
}
