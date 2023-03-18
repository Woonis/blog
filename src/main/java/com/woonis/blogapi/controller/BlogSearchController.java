package com.woonis.blogapi.controller;

import com.woonis.blogapi.controller.common.ResultResponse;
import com.woonis.blogapi.service.search.BlogSearchService;
import com.woonis.blogapi.service.search.dto.BlogSearchDto;
import com.woonis.blogapi.service.search.dto.BlogSearchPageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;

@Slf4j
@RestController
public class BlogSearchController {

    private final BlogSearchService service;

    public BlogSearchController(BlogSearchService service) {
        this.service = service;
    }

    @GetMapping("/api/v1/blog")
    public ResultResponse<BlogSearchPageDto> searchBlog(
            @Valid BlogSearchDto request,
            @Valid @Max(value = 50) @RequestParam(required = false, defaultValue = "1") int page,
            @Valid @Max(value = 50) @RequestParam(required = false, defaultValue = "10") int countPerPage
    ) {
        return ResultResponse.ok(service.search(request, page, countPerPage));
    }
}
