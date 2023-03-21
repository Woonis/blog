package sample.wooni.blog.service.search.external;


import sample.wooni.blog.service.output.blog.response.BlogPageDto;
import sample.wooni.blog.service.search.dto.BlogSearchDto;
import sample.wooni.blog.service.search.dto.BlogSearchType;

public interface ExternalBlogSearchService {
    boolean isTarget(BlogSearchType type);
    BlogPageDto search(BlogSearchDto request, int currentPage, int countPerPage);
}
