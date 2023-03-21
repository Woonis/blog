package sample.wooni.blog.service.statistics.converter;

import lombok.experimental.UtilityClass;
import sample.wooni.blog.entity.blog.statistics.BlogStatistics;
import sample.wooni.blog.service.statistics.dto.BlogStatisticsCreateDto;

@UtilityClass
public class BlogStatisticsCreateDtoConverter {
    public BlogStatistics convert(BlogStatisticsCreateDto createDto, int count) {
        return BlogStatistics.builder()
                .keyword(createDto.keyword())
                .type(createDto.type().name())
                .count(count)
                .build();
    }
}
