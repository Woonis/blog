package sample.wooni.blog.service.output.blog.statistics;

import sample.wooni.blog.entity.blog.statistics.BlogStatistics;
import sample.wooni.blog.service.search.dto.BlogSearchType;
import sample.wooni.blog.service.statistics.dto.BlogStatisticsCreateDto;

import java.util.List;
import java.util.Optional;

public interface BlogStatisticsOutput {
    Optional<BlogStatistics> findOneBy(BlogSearchType type, String keyword);
    List<BlogStatistics> fetchTop10Keyword(BlogSearchType type);
    BlogStatistics save(BlogStatistics blogStatistics);
}
