package com.woonis.blogapi.service.statistics;

import com.woonis.blogapi.repository.BlogStatisticsRepository;
import com.woonis.blogapi.service.search.dto.BlogSearchType;
import com.woonis.blogapi.service.statistics.converter.BlogStatisticsDtoConverter;
import com.woonis.blogapi.service.statistics.dto.BlogStatisticsDto;
import org.springframework.stereotype.Service;

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
