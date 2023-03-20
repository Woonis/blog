package sample.wooni.blog.service.statistics.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.ZonedDateTime;

public record BlogStatisticsDto(
        String keyword,
        String type,
        int count,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        ZonedDateTime createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        ZonedDateTime modifiedAt
) {
    @Builder
    public BlogStatisticsDto{
    }
}
