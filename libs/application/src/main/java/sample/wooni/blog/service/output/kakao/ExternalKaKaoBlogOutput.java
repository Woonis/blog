package sample.wooni.blog.service.output.kakao;

import sample.wooni.blog.service.output.blog.response.BlogPageDto;
import sample.wooni.blog.service.output.kakao.dto.request.KaKaoSearchRequest;

public interface ExternalKaKaoBlogOutput {
    BlogPageDto searchBlog(KaKaoSearchRequest searchDto);
}
