package sample.wooni.blog.client.naver.converter;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;
import sample.wooni.blog.service.common.Pagination;
import sample.wooni.blog.service.output.blog.response.BlogDetailDto;
import sample.wooni.blog.service.output.blog.response.BlogPageDto;
import sample.wooni.blog.service.output.kakao.dto.response.KaKaoBlogDetail;
import sample.wooni.blog.service.output.kakao.dto.response.KaKaoBlogResponse;
import sample.wooni.blog.service.output.naver.dto.response.NaverBlogDetail;
import sample.wooni.blog.service.output.naver.dto.response.NaverBlogResponse;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@UtilityClass
public class NaverBlogToBlogConverter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static BlogPageDto convert(NaverBlogResponse response) {
        if (Objects.isNull(response)) {
            return null;
        }

        return BlogPageDto.builder()
                .pagination(
                        new Pagination(response.total().intValue(), response.start(), response.display())
                )
                .documents(NaverBlogToBlogConverter.convert(response.items()))
                .build();
    }

    private List<BlogDetailDto> convert(List<NaverBlogDetail> documents) {
        if (CollectionUtils.isEmpty(documents)) {
            return Lists.newArrayList();
        }

        return documents.stream()
                .filter(Objects::nonNull)
                .map(it -> BlogDetailDto.builder()
                        .title(it.title())
                        .url(it.bloggerLink())
                        .name(it.bloggerName())
                        .content(it.description())
                        .createdAt(convertToDate(it.postDate()))
                        .build()
                )
                .collect(Collectors.toList());
    }

    private ZonedDateTime convertToDate(String date) {
        var ld = LocalDate.parse(date, FORMATTER);
        var lt = LocalTime.of(0, 0);

        return ZonedDateTime.of(LocalDateTime.of(ld, lt), ZoneId.of("Asia/Seoul"));
    }
}
