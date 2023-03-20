package sample.wooni.blog.controller;

import sample.wooni.blog.controller.common.ResultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sample.wooni.blog.service.search.dto.BlogSearchType;
import sample.wooni.blog.service.statistics.BlogStatisticsService;
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
}
