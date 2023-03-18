package com.woonis.blogapi.repository;

import com.woonis.blogapi.entity.BlogStatistics;
import com.woonis.blogapi.service.search.dto.BlogSearchType;

import java.util.List;

public interface BlogStatisticsRepository {
    List<BlogStatistics> fetchTop10Keyword(BlogSearchType type);
}
