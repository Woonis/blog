package sample.wooni.blog.service.search.external;


import sample.wooni.blog.service.search.dto.BlogSearchDto;
import sample.wooni.blog.service.search.dto.BlogSearchPageDto;
import sample.wooni.blog.service.search.dto.BlogSearchType;

public interface ExternalBlogSearchService {
    boolean isTarget(BlogSearchType type);
    BlogSearchPageDto search(BlogSearchDto request, int currentPage, int countPerPage);
}
