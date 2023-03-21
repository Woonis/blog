package sample.wooni.blog.service.statistics;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionOperations;
import sample.wooni.blog.service.output.blog.statistics.BlogStatisticsOutput;
import sample.wooni.blog.service.search.dto.BlogSearchType;
import sample.wooni.blog.service.statistics.converter.BlogStatisticsCreateDtoConverter;
import sample.wooni.blog.service.statistics.converter.BlogStatisticsDtoConverter;
import sample.wooni.blog.service.statistics.dto.BlogStatisticsCreateDto;
import sample.wooni.blog.service.statistics.dto.BlogStatisticsDto;

import java.util.List;
import java.util.Objects;

@Service
public class BlogStatisticsServiceImpl implements BlogStatisticsService {

    private final BlogStatisticsOutput blogStatisticsOutput;
    private final TransactionOperations readOperation;
    private final TransactionOperations writeOperation;

    public BlogStatisticsServiceImpl(BlogStatisticsOutput blogStatisticsOutput,
                                     TransactionOperations readOperation,
                                     TransactionOperations writeOperation) {
        this.blogStatisticsOutput = blogStatisticsOutput;
        this.readOperation = readOperation;
        this.writeOperation = writeOperation;
    }

    @Override
    public List<BlogStatisticsDto> top10SearchKeywords(BlogSearchType type) {
        var response = readOperation.execute( status -> blogStatisticsOutput.fetchTop10Keyword(type));
        return BlogStatisticsDtoConverter.convert(response);
    }

    @Override
    public BlogStatisticsDto save(BlogStatisticsCreateDto createDto) {
        if (!(createDto.type().isCreatableType())) {
            throw new IllegalArgumentException("create blog statistics type can't be all");
        }


        var response = writeOperation.execute(status -> {
            var exists = blogStatisticsOutput.findOneBy(createDto.type(), createDto.keyword()).orElse(null);
            if (Objects.nonNull(exists)) {
                exists.increaseCount();
                return exists;
            }

            var request = BlogStatisticsCreateDtoConverter.convert(createDto, NumberUtils.INTEGER_ONE);
            return blogStatisticsOutput.save(request);
        });

        return BlogStatisticsDtoConverter.convert(response);
    }
}
