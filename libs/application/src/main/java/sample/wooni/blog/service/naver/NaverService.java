package sample.wooni.blog.service.naver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sample.wooni.blog.service.naver.dto.NaverSearchSort;
import sample.wooni.blog.service.output.blog.response.BlogPageDto;
import sample.wooni.blog.service.output.naver.ExternalNaverBlogOutput;
import sample.wooni.blog.service.output.naver.dto.request.NaverSearchRequest;
import sample.wooni.blog.service.search.dto.BlogSearchDto;
import sample.wooni.blog.service.search.dto.BlogSearchType;
import sample.wooni.blog.service.search.external.ExternalBlogSearchService;

import java.util.Objects;

@Slf4j
@Service
public class NaverService implements ExternalBlogSearchService {
    private final ExternalNaverBlogOutput naverBlogOutput;

    public NaverService(ExternalNaverBlogOutput naverBlogOutput) {
        this.naverBlogOutput = naverBlogOutput;
    }

    @Override
    public boolean isTarget(BlogSearchType type) {
        return type == BlogSearchType.NAVER;
    }

    @Override
    public BlogPageDto search(BlogSearchDto request, int currentPage, int countPerPage) {
       return naverBlogOutput.search(
                NaverSearchRequest.builder()
                        .query(request.keyword())
                        .sort(Objects.nonNull(request.sort()) ? NaverSearchSort.findBy(request.sort()).name() : null)
                        .display(countPerPage)
                        .start(currentPage)
                        .build()
        );
    }
}
