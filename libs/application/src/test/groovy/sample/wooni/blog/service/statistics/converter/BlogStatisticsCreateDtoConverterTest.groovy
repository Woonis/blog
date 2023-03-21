package sample.wooni.blog.service.statistics.converter

import sample.wooni.blog.service.search.dto.BlogSearchType
import sample.wooni.blog.service.statistics.dto.BlogStatisticsCreateDto
import spock.lang.Specification

class BlogStatisticsCreateDtoConverterTest extends Specification {
    def "convert test"() {
        given:
        var request = BlogStatisticsCreateDto.builder()
        .keyword(keyword)
        .type(type)
        .build()

        when:
        var response = BlogStatisticsCreateDtoConverter.convert(request, count)

        then:
        response.keyword === expectedKeyword
        response.type === expectedType
        response.count === expectedCount

        where:
        keyword | type                  | count   | expectedKeyword     | expectedType | expectedCount
        null    | BlogSearchType.KAKAO  | 100     | null                | "KAKAO"      | 100
        ""      | BlogSearchType.NAVER  | 1       | ""                  | "NAVER"      | 1
    }
}
