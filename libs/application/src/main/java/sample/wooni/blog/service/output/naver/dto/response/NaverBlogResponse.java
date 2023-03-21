package sample.wooni.blog.service.output.naver.dto.response;

import java.util.List;

public record NaverBlogResponse(
        String lastBuildDate,
        Long total,
        Integer start,
        Integer display,
        List<NaverBlogDetail> items
) {
}
