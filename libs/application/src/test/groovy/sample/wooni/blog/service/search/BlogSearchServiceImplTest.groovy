package sample.wooni.blog.service.search

import org.assertj.core.util.Lists
import sample.wooni.blog.service.common.Pagination
import sample.wooni.blog.service.kakao.KaKaoService
import sample.wooni.blog.service.kakao.dto.KaKaoBlogDetailDto
import sample.wooni.blog.service.kakao.dto.KaKaoBlogPageDto
import sample.wooni.blog.service.kakao.dto.KaKaoSearchSort
import sample.wooni.blog.service.search.dto.BlogSearchDto
import sample.wooni.blog.service.search.dto.BlogSearchType
import sample.wooni.blog.service.statistics.BlogStatisticsService
import sample.wooni.blog.service.statistics.dto.BlogStatisticsDto
import spock.lang.Specification

import java.time.ZonedDateTime

class BlogSearchServiceImplTest extends Specification {
    private BlogSearchServiceImpl service
    private KaKaoService kaKaoService
    private BlogStatisticsService statisticsService

    def "setup"() {
        kaKaoService = Mock(KaKaoService.class)
        statisticsService = Mock(BlogStatisticsService.class)

        service = new BlogSearchServiceImpl(
                Lists.newArrayList(
                        kaKaoService
                ),
                statisticsService
        )
    }

    def "search"() {
        given:
        var request = BlogSearchDto.builder()
                .keyword("hello")
                .type(BlogSearchType.KAKAO)
                .url(null)
                .sort(KaKaoSearchSort.NONE)
                .build()

        1 * kaKaoService.isTarget(BlogSearchType.KAKAO) >> true
        1 * kaKaoService.search(_, _, _) >> KaKaoBlogPageDto.builder()
                .pagination(new Pagination(100, 10, true, 0, 10))
                .documents(Lists.newArrayList(
                        KaKaoBlogDetailDto.builder()
                                .title("이스트맨하우스 매트리스 매장 고척 가구 <b>123</b>")
                                .contents("이번 참에 매트리스 교체하고 싶다는 생각이 들어서 또 침대를 사용해볼까 하다가")
                                .url("https://blog.naver.com/mhhan71/222984242889")
                                .thumbnail("https://search3.kakaocdn.net/argon/130x130_85_c/D0oLzGcglGH")
                                .createdAt(ZonedDateTime.now())
                                .build()
                ))
        .build()
        1 * statisticsService.save(_) >> BlogStatisticsDto.builder().build()

        when:
        KaKaoBlogPageDto response = service.search(request, 0, 10)

        then:
        noExceptionThrown()
        response.getPagination() != null
        response.getDocuments() != null
        response.getDocuments().size() == 1
        response.getDocuments().get(0).title() == "이스트맨하우스 매트리스 매장 고척 가구 <b>123</b>"
    }

    def "not found target service"() {
        given:
        var request = BlogSearchDto.builder()
                .keyword("hello")
                .type(BlogSearchType.ALL)
                .url(null)
                .sort(KaKaoSearchSort.NONE)
                .build()

        when:
        service.search(request, 0, 10)

        then:
        def e = thrown(IllegalArgumentException.class)
        e.message == "Not found matched External Search Service"
    }
}
