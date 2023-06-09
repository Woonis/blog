package sample.wooni.blog.service.statistics.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import sample.wooni.blog.service.search.dto.BlogSearchType;

public record BlogStatisticsCreateDto(
        @NotBlank String keyword,
        @NotNull BlogSearchType type
) {
    @Builder
    public BlogStatisticsCreateDto{
    }
}
