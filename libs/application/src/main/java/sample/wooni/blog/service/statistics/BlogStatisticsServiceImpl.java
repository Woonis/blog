package sample.wooni.blog.service.statistics;

import org.springframework.stereotype.Service;
import sample.wooni.blog.service.output.blog.statistics.BlogStatisticsOutput;
import sample.wooni.blog.service.search.dto.BlogSearchType;
import sample.wooni.blog.service.statistics.converter.BlogStatisticsDtoConverter;
import sample.wooni.blog.service.statistics.dto.BlogStatisticsDto;

import java.util.List;

@Service
public class BlogStatisticsServiceImpl implements BlogStatisticsService {

    private final BlogStatisticsOutput blogStatisticsOutput;

    public BlogStatisticsServiceImpl(BlogStatisticsOutput blogStatisticsOutput) {
        this.blogStatisticsOutput = blogStatisticsOutput;
    }

    @Override
    public List<BlogStatisticsDto> top10SearchKeywords(BlogSearchType type) {
        var response = blogStatisticsOutput.fetchTop10Keyword(type);
        return BlogStatisticsDtoConverter.convert(response);
    }
}
