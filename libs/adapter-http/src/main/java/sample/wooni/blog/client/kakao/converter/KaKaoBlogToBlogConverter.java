package sample.wooni.blog.client.kakao.converter;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;
import sample.wooni.blog.service.common.Pagination;
import sample.wooni.blog.service.output.blog.response.BlogPageDto;
import sample.wooni.blog.service.output.blog.response.BlogDetailDto;
import sample.wooni.blog.service.output.kakao.dto.response.KaKaoBlogDetail;
import sample.wooni.blog.service.output.kakao.dto.response.KaKaoBlogResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@UtilityClass
public class KaKaoBlogToBlogConverter {
    public static BlogPageDto convert(KaKaoBlogResponse response, int currentPage, int countPerPage) {
        if (Objects.isNull(response)) {
            return null;
        }

        var meta = response.meta();
        return BlogPageDto.builder()
                .pagination(
                        new Pagination(meta.pageableCount(), meta.totalCount(), !meta.isEnd(), currentPage, countPerPage)
                )
                .documents(KaKaoBlogToBlogConverter.convert(response.documents()))
                .build();
    }

    private List<BlogDetailDto> convert(List<KaKaoBlogDetail> documents) {
        if (CollectionUtils.isEmpty(documents)) {
            return Lists.newArrayList();
        }

        return documents.stream()
                .filter(Objects::nonNull)
                .map(it -> BlogDetailDto.builder()
                        .title(it.title())
                        .url(it.url())
                        .name(it.blogName())
                        .content(it.contents())
                        .thumbnail(it.thumbnail())
                        .createdAt(it.createdAt())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
