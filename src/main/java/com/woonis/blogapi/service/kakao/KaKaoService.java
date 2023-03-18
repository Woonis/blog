package com.woonis.blogapi.service.kakao;

import com.woonis.blogapi.client.kakao.KaKaoClient;
import com.woonis.blogapi.client.kakao.dto.request.KaKaoSearchRequest;
import com.woonis.blogapi.client.kakao.dto.response.KaKaoBlogResponse;
import com.woonis.blogapi.converter.KaKaoBlogDtoConverter;
import com.woonis.blogapi.service.common.Pagination;
import com.woonis.blogapi.service.kakao.dto.KaKaoBlogPageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KaKaoService {

    private final KaKaoClient client;

    public KaKaoService(KaKaoClient client) {
        this.client = client;
    }

    public KaKaoBlogPageDto search() {
        KaKaoBlogResponse response = client.searchBlog(
                KaKaoSearchRequest.builder()
                        .keyword("브런치")
                        .build()
        );

        return KaKaoBlogPageDto.builder()
                .documents(KaKaoBlogDtoConverter.convert(response.documents()))
                .pagination(
                        Pagination.builder()
                                .currentPage(1)
                                .countPerPage(10)
                                .kaKaoMeta(response.meta())
                                .build()
                )
                .build();
    }
}
