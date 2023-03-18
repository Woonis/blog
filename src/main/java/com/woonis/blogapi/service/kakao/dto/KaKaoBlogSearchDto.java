package com.woonis.blogapi.service.kakao.dto;

import jakarta.validation.constraints.NotBlank;

public record KaKaoBlogSearchDto(
        @NotBlank(message = "키워드는 필수값 입니다.")
        String keyword,
        String url,
        KaKaoSearchSort sort
) {
}
