package com.woonis.blogapi.service.search;

import com.woonis.blogapi.service.search.dto.BlogSearchDto;
import com.woonis.blogapi.service.search.dto.BlogSearchPageDto;

public interface BlogSearchService {
    BlogSearchPageDto search(BlogSearchDto request, int currentPage, int countPerPage);
}
