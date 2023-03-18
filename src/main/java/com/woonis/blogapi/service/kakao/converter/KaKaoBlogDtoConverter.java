package com.woonis.blogapi.service.kakao.converter;

import com.google.common.collect.Lists;
import com.woonis.blogapi.client.kakao.dto.response.KaKaoBlogDetail;
import com.woonis.blogapi.service.kakao.dto.KaKaoBlogDetailDto;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@UtilityClass
public class KaKaoBlogDtoConverter {
    public static List<KaKaoBlogDetailDto> convert(List<KaKaoBlogDetail> target) {
        if (CollectionUtils.isEmpty(target)) {
            return Lists.newArrayList();
        }

        return target.stream()
                .filter(Objects::nonNull)
                .map(KaKaoBlogDtoConverter::convertDto)
                .collect(Collectors.toList());
    }

    private KaKaoBlogDetailDto convertDto(KaKaoBlogDetail target) {
        return KaKaoBlogDetailDto.builder()
                .title(target.title())
                .contents(target.contents())
                .url(target.url())
                .blogName(target.blogName())
                .thumbnail(target.thumbnail())
                .createdAt(target.createdAt())
                .build();
    }
}
