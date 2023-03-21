package sample.wooni.blog.service.statistics

import org.assertj.core.util.Lists
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.support.TransactionOperations
import sample.wooni.blog.entity.blog.statistics.BlogStatistics
import sample.wooni.blog.service.output.blog.statistics.BlogStatisticsOutput
import sample.wooni.blog.service.search.dto.BlogSearchType
import sample.wooni.blog.service.statistics.dto.BlogStatisticsCreateDto
import spock.lang.Specification

class BlogStatisticsServiceImplTest extends Specification {
    private BlogStatisticsServiceImpl service

    @Autowired
    private BlogStatisticsOutput blogStatisticsOutput
    private TransactionOperations readOperation
    private TransactionOperations writeOperation

    def "setup"() {
        readOperation = Mock(TransactionOperations.class)
        writeOperation = Mock(TransactionOperations.class)

        service = new BlogStatisticsServiceImpl(
                blogStatisticsOutput, readOperation, writeOperation
        )
    }

    def "topSearchKeywords"() {
        given:
        1 * readOperation.execute(_) >> Lists.newArrayList(BlogStatistics.builder()
                .type("KAKAO")
                .keyword("test")
                .count(100)
                .build()
        )

        when:
        var response = service.topSearchKeywords(BlogSearchType.KAKAO, 10)

        then:
        noExceptionThrown()
        response != null
        response.size() == 1
    }

    def "save"() {
        given:
        var request = BlogStatisticsCreateDto.builder()
                .type(BlogSearchType.KAKAO)
                .keyword("test")
                .build()

        1 * writeOperation.execute (_) >> BlogStatistics.builder()
                .type("KAKAO")
                .keyword("test")
                .count(100)
                .build()

        when:
        service.save(request)

        then:
        noExceptionThrown()
    }

    def "save with invalid type"() {
        given:
        var request = BlogStatisticsCreateDto.builder()
        .type(BlogSearchType.ALL)
        .build()

        when:
        service.save(request)

        then:
        var e = thrown(IllegalArgumentException.class)
        e.message == "create blog statistics type can't be all"
    }
}
