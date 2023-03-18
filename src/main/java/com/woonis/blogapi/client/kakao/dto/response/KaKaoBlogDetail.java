package com.woonis.blogapi.client.kakao.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.beans.ConstructorProperties;
import java.time.ZonedDateTime;

public record KaKaoBlogDetail(
        String title,
        String contents,
        String url,
        @JsonProperty("blogname") String blogName,
        String thumbnail,
        @JsonProperty("datetime") ZonedDateTime createdAt
) {
    @ConstructorProperties({"title", "contents", "url", "blogName", "thumbnail", "createdAt"})
    public KaKaoBlogDetail(String title,
                           String contents,
                           String url,
                           String blogName,
                           String thumbnail,
                           ZonedDateTime createdAt) {
        this.title = title;
        this.contents = contents;
        this.url = url;
        this.blogName = blogName;
        this.thumbnail = thumbnail;
        this.createdAt = createdAt;
    }
}
