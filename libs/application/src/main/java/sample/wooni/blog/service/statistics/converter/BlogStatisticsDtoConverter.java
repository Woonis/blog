package sample.wooni.blog.service.statistics.converter;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;
import sample.wooni.blog.service.entity.BlogStatistics;
import sample.wooni.blog.service.statistics.dto.BlogStatisticsDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@UtilityClass
public class BlogStatisticsDtoConverter {
    public static List<BlogStatisticsDto> convert(List<BlogStatistics> targets) {
        if (CollectionUtils.isEmpty(targets)) {
            return Lists.newArrayList();
        }

        return targets.stream()
                .filter(Objects::nonNull)
                .map(BlogStatisticsDtoConverter::convertDto)
                .collect(Collectors.toList());
    }

    private BlogStatisticsDto convertDto(BlogStatistics target) {
        return BlogStatisticsDto.builder()
                .keyword(target.getKeyword())
                .count(target.getCount())
                .createdAt(target.getCreatedAt())
                .modifiedAt(target.getModifiedAt())
                .build();
    }
}
