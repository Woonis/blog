package sample.wooni.blog.statistics;

import org.springframework.stereotype.Component;
import sample.wooni.blog.entity.blog.statistics.BlogStatistics;
import sample.wooni.blog.service.output.blog.statistics.BlogStatisticsOutput;
import sample.wooni.blog.service.search.dto.BlogSearchType;

import java.util.List;
import java.util.Optional;

@Component
public class BlogStatisticsRepository implements BlogStatisticsOutput {
    private final BlogStatisticsJpaRepository repository;

    public BlogStatisticsRepository(BlogStatisticsJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<BlogStatistics> findOneBy(BlogSearchType type, String keyword) {
        return repository.findOneBy(type, keyword);
    }

    @Override
    public List<BlogStatistics> fetchTopKeyword(BlogSearchType type, int limit) {
        return repository.fetchTopKeyword(type, limit);
    }

    @Override
    public BlogStatistics save(BlogStatistics blogStatistics) {
        return repository.save(blogStatistics);
    }


}
