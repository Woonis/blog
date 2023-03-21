package sample.wooni.blog.service.naver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.ZonedDateTime;

public record NaverBlogDetailDto(
        String title,
        String link,
        String description,
        String bloggerName,
        String bloggerLink,
        @JsonFormat(pattern = "yyyy-MM-dd") ZonedDateTime postDate
) {

    @Builder
    public NaverBlogDetailDto{
    }
}
