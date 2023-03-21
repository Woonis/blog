package sample.wooni.blog.service.naver.dto;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

public enum NaverSearchSort {
    SIM("sim"),
    DATE("date"),
    NONE("");

    @Getter
    private final String description;

    NaverSearchSort(String description) {
        this.description = description;
    }

    public static NaverSearchSort findBy(String value) {
        return Arrays.stream(NaverSearchSort.values())
                .filter(it -> Objects.equals(it.name(), value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Can't find NaverSearchSort"));
    }
}
