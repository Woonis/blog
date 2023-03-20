package sample.wooni.blog.service.search;


import sample.wooni.blog.service.search.dto.BlogSearchDto;
import sample.wooni.blog.service.search.dto.BlogSearchPageDto;

public interface BlogSearchService {
    BlogSearchPageDto search(BlogSearchDto request, int currentPage, int countPerPage);
}
