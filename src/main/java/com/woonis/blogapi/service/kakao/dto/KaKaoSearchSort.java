package com.woonis.blogapi.service.kakao.dto;

import lombok.Getter;

public enum KaKaoSearchSort {
    ACCURACY("accuracy"),
    RECENCY("recency"),
    NONE("");

    @Getter
    private final String description;

    KaKaoSearchSort(String description) {
        this.description = description;
    }


}
