package sample.wooni.blog.service.output.naver;

import sample.wooni.blog.service.output.blog.response.BlogPageDto;
import sample.wooni.blog.service.output.naver.dto.request.NaverSearchRequest;
import sample.wooni.blog.service.output.naver.dto.response.NaverBlogResponse;

public interface ExternalNaverBlogOutput{
    BlogPageDto search(NaverSearchRequest request);
}
