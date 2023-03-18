package com.woonis.blogapi.service.search.external;

import com.woonis.blogapi.service.search.dto.BlogSearchDto;
import com.woonis.blogapi.service.search.dto.BlogSearchPageDto;
import com.woonis.blogapi.service.search.dto.BlogSearchType;

public interface ExternalBlogSearchService {
    boolean isTarget(BlogSearchType type);
    BlogSearchPageDto search(BlogSearchDto request, int currentPage, int countPerPage);
}
