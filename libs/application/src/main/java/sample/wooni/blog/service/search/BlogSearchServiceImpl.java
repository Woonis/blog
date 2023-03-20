package sample.wooni.blog.service.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sample.wooni.blog.service.search.dto.BlogSearchDto;
import sample.wooni.blog.service.search.dto.BlogSearchPageDto;
import sample.wooni.blog.service.search.external.ExternalBlogSearchService;

import java.util.List;

@Slf4j
@Service
public class BlogSearchServiceImpl implements BlogSearchService {

    private final List<ExternalBlogSearchService> services;

    public BlogSearchServiceImpl(List<ExternalBlogSearchService> services) {
        this.services = services;
    }

    @Override
    public BlogSearchPageDto search(BlogSearchDto request, int currentPage, int countPerPage) {
        ExternalBlogSearchService service = services.stream()
                .filter(it -> it.isTarget(request.type()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Not found matched External Search Service"));


        return service.search(request, currentPage, countPerPage);
    }
}
