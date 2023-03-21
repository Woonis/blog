package sample.wooni.blog.service.output.blog.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.ZonedDateTime;

public record BlogDetailDto(
        String title,
        String content,
        String url,
        String name,
        String thumbnail,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") ZonedDateTime createdAt
) {
    @Builder
    public BlogDetailDto {
    }
}
