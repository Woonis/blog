package sample.wooni.blog.service.statistics;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionOperations;
import sample.wooni.blog.service.output.blog.statistics.BlogStatisticsOutput;
import sample.wooni.blog.service.search.dto.BlogSearchType;
import sample.wooni.blog.service.statistics.converter.BlogStatisticsDtoConverter;
import sample.wooni.blog.service.statistics.dto.BlogStatisticsDto;

import java.util.List;

@Service
public class BlogStatisticsServiceImpl implements BlogStatisticsService {

    private final BlogStatisticsOutput blogStatisticsOutput;
    private final TransactionOperations readOperation;

    public BlogStatisticsServiceImpl(BlogStatisticsOutput blogStatisticsOutput,
                                     TransactionOperations readOperation) {
        this.blogStatisticsOutput = blogStatisticsOutput;
        this.readOperation = readOperation;
    }

    @Override
    public List<BlogStatisticsDto> top10SearchKeywords(BlogSearchType type) {
        var response = readOperation.execute( status -> blogStatisticsOutput.fetchTop10Keyword(type));
        return BlogStatisticsDtoConverter.convert(response);
    }
}
