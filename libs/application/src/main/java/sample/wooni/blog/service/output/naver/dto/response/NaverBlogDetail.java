package sample.wooni.blog.service.output.naver.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NaverBlogDetail(
        String title,
        String link,
        String description,
        @JsonProperty("bloggername") String bloggerName,
        @JsonProperty("bloggerlink") String bloggerLink,
        @JsonProperty("postdate") String postDate
) {
}
