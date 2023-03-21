package sample.wooni.blog.service.search;


import sample.wooni.blog.service.output.blog.response.BlogPageDto;
import sample.wooni.blog.service.search.dto.BlogSearchDto;
import sample.wooni.blog.service.search.dto.BlogSearchPageDto;

public interface BlogSearchService {
    BlogPageDto search(BlogSearchDto request, int currentPage, int countPerPage);
}
