package com.woonis.blogapi.client.kakao.dto.request;

import io.micrometer.common.util.StringUtils;
import lombok.Builder;

public record KaKaoSearchRequest(String keyword, String url, String sort, int page, int size) {
    @Builder
    public KaKaoSearchRequest {
    }

    public String getQuery() {
        if (StringUtils.isNotBlank(this.url)) {
            return String.format("%s %s", this.url, this.keyword);
        }

        return StringUtils.isNotBlank(this.keyword) ? this.keyword : "";
    }

    public String getSort() {
        return StringUtils.isNotBlank(this.sort) ? this.sort : "";
    }

    public int getPage() {
        return this.page > 0 ? this.page : 1;
    }

    public int getSize() {
        return this.size > 0 ? this.size : 1;
    }
}
