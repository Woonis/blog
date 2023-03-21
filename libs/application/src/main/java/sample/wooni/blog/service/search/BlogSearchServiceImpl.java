package sample.wooni.blog.service.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sample.wooni.blog.service.output.blog.response.BlogPageDto;
import sample.wooni.blog.service.search.dto.BlogSearchDto;
import sample.wooni.blog.service.search.external.ExternalBlogSearchService;
import sample.wooni.blog.service.statistics.BlogStatisticsService;
import sample.wooni.blog.service.statistics.dto.BlogStatisticsCreateDto;

import java.util.List;

@Slf4j
@Service
public class BlogSearchServiceImpl implements BlogSearchService {

    private final List<ExternalBlogSearchService> services;
    private final BlogStatisticsService statisticsService;

    public BlogSearchServiceImpl(List<ExternalBlogSearchService> services,
                                 BlogStatisticsService statisticsService) {
        this.services = services;
        this.statisticsService = statisticsService;
    }

    @Override
    public BlogPageDto search(BlogSearchDto request, int currentPage, int countPerPage) {
        ExternalBlogSearchService service = services.stream()
                .filter(it -> it.isTarget(request.type()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Not found matched External Search Service"));


        // 통계 저장
        statisticsService.save(
                BlogStatisticsCreateDto.builder()
                        .keyword(request.keyword())
                        .type(request.type())
                        .build()
        );

        return service.search(request, currentPage, countPerPage);
    }
}
