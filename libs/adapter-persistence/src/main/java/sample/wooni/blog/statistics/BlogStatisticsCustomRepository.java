package sample.wooni.blog.statistics;

import sample.wooni.blog.entity.blog.statistics.BlogStatistics;
import sample.wooni.blog.service.search.dto.BlogSearchType;

import java.util.List;
import java.util.Optional;

public interface BlogStatisticsCustomRepository {
    Optional<BlogStatistics> findOneBy(BlogSearchType type, String keyword);
    List<BlogStatistics> fetchTopKeyword(BlogSearchType type, int limit);
}
