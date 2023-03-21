package sample.wooni.blog.service.search.dto;

import lombok.Getter;

import java.util.Arrays;

public enum BlogSearchType {
    ALL(false),
    KAKAO(true),
    NAVER(true);

    @Getter
    private final boolean creatableType;

    BlogSearchType(boolean creatableType) {
        this.creatableType = creatableType;
    }
}
