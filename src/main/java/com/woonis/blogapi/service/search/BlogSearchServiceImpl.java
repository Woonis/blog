package com.woonis.blogapi.service.search;

import com.woonis.blogapi.service.search.dto.BlogSearchDto;
import com.woonis.blogapi.service.search.dto.BlogSearchPageDto;
import com.woonis.blogapi.service.search.external.ExternalBlogSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BlogSearchServiceImpl implements BlogSearchService {

    private final List<ExternalBlogSearchService> services;

    public BlogSearchServiceImpl(List<ExternalBlogSearchService> services) {
        this.services = services;
    }

    @Override
    public BlogSearchPageDto search(BlogSearchDto request, int currentPage, int countPerPage) {
        ExternalBlogSearchService service = services.stream()
                .filter(it -> it.isTarget(request.type()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Not found matched External Search Service"));


        return service.search(request, currentPage, countPerPage);
    }
}
