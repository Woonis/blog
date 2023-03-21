package sample.wooni.blog.service.statistics.converter

import sample.wooni.blog.entity.blog.statistics.BlogStatistics
import spock.lang.Specification

class BlogStatisticsDtoConverterTest extends Specification {
    def "convert test"() {
        given:
        var request = BlogStatistics.builder()
                .id(1)
                .keyword("test")
                .type("KAKAO")
                .count(100)
                .build()

        when:
        var response = BlogStatisticsDtoConverter.convert(request)

        then:
        response.keyword() === "test"
        response.type() === "KAKAO"
        response.count() == 100
    }
}
