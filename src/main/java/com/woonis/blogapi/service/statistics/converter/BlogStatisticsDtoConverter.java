package com.woonis.blogapi.service.statistics.converter;

import com.google.common.collect.Lists;
import com.woonis.blogapi.entity.BlogStatistics;
import com.woonis.blogapi.service.statistics.dto.BlogStatisticsDto;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

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
