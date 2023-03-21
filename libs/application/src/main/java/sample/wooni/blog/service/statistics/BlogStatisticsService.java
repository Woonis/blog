package sample.wooni.blog.service.statistics;


import sample.wooni.blog.service.search.dto.BlogSearchType;
import sample.wooni.blog.service.statistics.dto.BlogStatisticsCreateDto;
import sample.wooni.blog.service.statistics.dto.BlogStatisticsDto;

import java.util.List;

public interface BlogStatisticsService {
    List<BlogStatisticsDto> topSearchKeywords(BlogSearchType type, int limit);
    BlogStatisticsDto save(BlogStatisticsCreateDto createDto);
}
