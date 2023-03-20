package sample.wooni.blog.service.statistics;

import org.springframework.stereotype.Service;
import sample.wooni.blog.service.repository.BlogStatisticsRepository;
import sample.wooni.blog.service.search.dto.BlogSearchType;
import sample.wooni.blog.service.statistics.converter.BlogStatisticsDtoConverter;
import sample.wooni.blog.service.statistics.dto.BlogStatisticsDto;

import java.util.List;

@Service
public class BlogStatisticsServiceImpl implements BlogStatisticsService {

    private final BlogStatisticsRepository repository;

    public BlogStatisticsServiceImpl(BlogStatisticsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<BlogStatisticsDto> top10SearchKeywords(BlogSearchType type) {
        var response = repository.fetchTop10Keyword(type);
        return BlogStatisticsDtoConverter.convert(response);
    }
}
