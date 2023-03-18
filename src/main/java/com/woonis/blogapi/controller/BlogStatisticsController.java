package com.woonis.blogapi.controller;

import com.woonis.blogapi.controller.common.ResultResponse;
import com.woonis.blogapi.service.search.dto.BlogSearchType;
import com.woonis.blogapi.service.statistics.BlogStatisticsService;
import com.woonis.blogapi.service.statistics.dto.BlogStatisticsDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
