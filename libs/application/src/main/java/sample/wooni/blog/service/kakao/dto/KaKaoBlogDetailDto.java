package sample.wooni.blog.service.kakao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.ZonedDateTime;

public record KaKaoBlogDetailDto(
        String title,
        String contents,
        String url,
       String blogName,
        String thumbnail,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") ZonedDateTime createdAt
) {

    @Builder
    public KaKaoBlogDetailDto {
    }
}
