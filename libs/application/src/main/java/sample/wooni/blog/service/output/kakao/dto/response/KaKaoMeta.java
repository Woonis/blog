package sample.wooni.blog.service.output.kakao.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.beans.ConstructorProperties;

public record KaKaoMeta(
        @JsonProperty("total_count") int totalCount,
        @JsonProperty("pageable_count") int pageableCount,
        @JsonProperty("is_end") boolean isEnd
){
    @ConstructorProperties({"totalCount", "pageableCount", "isEnd"})
    public KaKaoMeta(int totalCount, int pageableCount, boolean isEnd) {
        this.totalCount = totalCount;
        this.pageableCount = pageableCount;
        this.isEnd = isEnd;
    }
}
