package sample.wooni.blog.service.statistics;


import sample.wooni.blog.service.search.dto.BlogSearchType;
import sample.wooni.blog.service.statistics.dto.BlogStatisticsCreateDto;
import sample.wooni.blog.service.statistics.dto.BlogStatisticsDto;

import java.util.List;

public interface BlogStatisticsService {
    List<BlogStatisticsDto> top10SearchKeywords(BlogSearchType type);
    BlogStatisticsDto save(BlogStatisticsCreateDto createDto);
}
