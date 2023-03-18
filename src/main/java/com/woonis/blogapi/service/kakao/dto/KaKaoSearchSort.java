package com.woonis.blogapi.service.kakao.dto;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

public enum KaKaoSearchSort {
    ACCURACY("accuracy"),
    RECENCY("recency"),
    NONE("");

    @Getter
    private final String description;

    KaKaoSearchSort(String description) {
        this.description = description;
    }

    public static KaKaoSearchSort findBy(String value) {
        return Arrays.stream(KaKaoSearchSort.values())
                .filter(it -> Objects.equals(it.name(), value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Can't find KaKaoSearchSort"));
    }
}
