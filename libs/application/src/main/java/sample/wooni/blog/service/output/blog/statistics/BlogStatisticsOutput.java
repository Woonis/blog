package sample.wooni.blog.service.output.blog.statistics;

import sample.wooni.blog.entity.blog.statistics.BlogStatistics;
import sample.wooni.blog.service.search.dto.BlogSearchType;

import java.util.List;

public interface BlogStatisticsOutput {
    List<BlogStatistics> fetchTop10Keyword(BlogSearchType type);
}
