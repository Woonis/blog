package sample.wooni.blog.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import sample.wooni.blog.controller.common.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sample.wooni.blog.service.output.blog.response.BlogPageDto;
import sample.wooni.blog.service.search.BlogSearchService;
import sample.wooni.blog.service.search.dto.BlogSearchDto;
import sample.wooni.blog.service.search.dto.BlogSearchPageDto;

@Slf4j
@RestController
public class BlogSearchController {

    private final BlogSearchService service;

    public BlogSearchController(BlogSearchService service) {
        this.service = service;
    }

    @GetMapping("/api/v1/blog")
    public ResultResponse<BlogPageDto> searchBlog(
            @Valid BlogSearchDto request,
            @Valid @Max(value = 50) @RequestParam(required = false, defaultValue = "1") int page,
            @Valid @Max(value = 50) @RequestParam(required = false, defaultValue = "10") int countPerPage
    ) {
        return ResultResponse.ok(service.search(request, page, countPerPage));
    }
}
