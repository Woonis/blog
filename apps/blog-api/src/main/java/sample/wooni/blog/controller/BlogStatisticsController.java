package sample.wooni.blog.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import sample.wooni.blog.controller.common.ResultResponse;
import sample.wooni.blog.service.search.dto.BlogSearchType;
import sample.wooni.blog.service.statistics.BlogStatisticsService;
import sample.wooni.blog.service.statistics.dto.BlogStatisticsCreateDto;
import sample.wooni.blog.service.statistics.dto.BlogStatisticsDto;

import java.util.List;

@RestController
public class BlogStatisticsController {
    private final BlogStatisticsService service;

    public BlogStatisticsController(BlogStatisticsService service) {
        this.service = service;
    }

    @GetMapping("/api/v1/blog/statistics/top-10-keyword")
    public ResultResponse<List<BlogStatisticsDto>> top10SearchKeywords(@RequestParam BlogSearchType type) {
        return ResultResponse.ok(service.top10SearchKeywords(type));
    }

    @PostMapping("/api/v1/blog/statistics")
    public ResultResponse<BlogStatisticsDto> save(@Valid @RequestBody BlogStatisticsCreateDto request) {
        return ResultResponse.ok(service.save(request));
    }
}
