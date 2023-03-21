package sample.wooni.blog.service.naver.converter;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;
import sample.wooni.blog.service.naver.dto.NaverBlogDetailDto;
import sample.wooni.blog.service.output.naver.dto.response.NaverBlogDetail;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@UtilityClass
public class NaverBlogDtoConverter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static List<NaverBlogDetailDto> convert(List<NaverBlogDetail> targets) {
        if (CollectionUtils.isEmpty(targets)) {
            return Lists.newArrayList();
        }

        return targets.stream()
                .filter(Objects::nonNull)
                .map(it -> NaverBlogDetailDto.builder()
                        .title(it.title())
                        .link(it.link())
                        .description(it.description())
                        .bloggerName(it.bloggerName())
                        .bloggerLink(it.bloggerLink())
                        .postDate(convertToDate(it.postDate()))
                        .build()
                ).collect(Collectors.toList());
    }


    private ZonedDateTime convertToDate(String date) {
        var ld = LocalDate.parse(date, FORMATTER);
        var lt = LocalTime.of(0, 0);

        return ZonedDateTime.of(LocalDateTime.of(ld, lt), ZoneId.of("Asia/Seoul"));
    }
}
