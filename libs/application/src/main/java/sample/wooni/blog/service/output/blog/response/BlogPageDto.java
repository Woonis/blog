package sample.wooni.blog.service.output.blog.response;

import lombok.Builder;
import sample.wooni.blog.service.common.Pagination;

import java.util.List;

public record BlogPageDto(
        List<BlogDetailDto> documents,
        Pagination pagination
) {

    @Builder
    public BlogPageDto {
    }
}
