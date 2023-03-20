package sample.wooni.blog.service.repository;


import sample.wooni.blog.service.entity.BlogStatistics;
import sample.wooni.blog.service.search.dto.BlogSearchType;

import java.util.List;

public interface BlogStatisticsRepository {
    List<BlogStatistics> fetchTop10Keyword(BlogSearchType type);
}
