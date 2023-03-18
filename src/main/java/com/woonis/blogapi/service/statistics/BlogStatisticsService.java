package com.woonis.blogapi.service.statistics;

import com.woonis.blogapi.service.search.dto.BlogSearchType;
import com.woonis.blogapi.service.statistics.dto.BlogStatisticsDto;

import java.util.List;

public interface BlogStatisticsService {
    List<BlogStatisticsDto> top10SearchKeywords(BlogSearchType type);
}
