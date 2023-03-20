package sample.wooni.blog.service.output.kakao;

import sample.wooni.blog.service.output.kakao.dto.request.KaKaoSearchRequest;
import sample.wooni.blog.service.output.kakao.dto.response.KaKaoBlogResponse;

public interface ExternalKaKaoBlogOutput {
    KaKaoBlogResponse searchBlog(KaKaoSearchRequest searchDto);
}
