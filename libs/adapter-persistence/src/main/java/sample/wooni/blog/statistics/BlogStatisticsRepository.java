package sample.wooni.blog.statistics;

import org.springframework.stereotype.Component;
import sample.wooni.blog.entity.blog.statistics.BlogStatistics;
import sample.wooni.blog.service.output.blog.statistics.BlogStatisticsOutput;
import sample.wooni.blog.service.search.dto.BlogSearchType;

import java.util.List;

@Component
public class BlogStatisticsRepository implements BlogStatisticsOutput {
    private final BlogStatisticsJpaRepository repository;

    public BlogStatisticsRepository(BlogStatisticsJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<BlogStatistics> fetchTop10Keyword(BlogSearchType type) {
        return repository.fetchTop10Keyword(type);
    }
}
