package sample.wooni.blog.service.output.naver.dto.request;

import io.micrometer.common.util.StringUtils;
import lombok.Builder;
import sample.wooni.blog.service.naver.dto.NaverSearchSort;

public record NaverSearchRequest(
        String query,
        int display,
        int start,
        String sort
) {
    @Builder
    public NaverSearchRequest{
    }
    public String getSort() {
        return StringUtils.isNotBlank(this.sort) ? this.sort : "sim";
    }

    public int getDisplay() {
        return this.display > 0 ? this.display : 10;
    }

    public int getStart() {
        return this.start > 0 ? this.start : 1;
    }
}
