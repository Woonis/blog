package sample.wooni.blog.client.kakao;

import com.google.common.collect.Maps;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sample.wooni.blog.client.kakao.converter.KaKaoBlogToBlogConverter;
import sample.wooni.blog.service.output.blog.response.BlogPageDto;
import sample.wooni.blog.service.output.kakao.ExternalKaKaoBlogOutput;
import sample.wooni.blog.service.output.kakao.dto.request.KaKaoSearchRequest;
import sample.wooni.blog.service.output.kakao.dto.response.KaKaoBlogResponse;

import java.util.Map;

@Service
public class KaKaoClient implements ExternalKaKaoBlogOutput {
    private final String searchUrl;
    private final String apiKey;
    private final RestTemplate restTemplate;

    public KaKaoClient(@Value("${api.kakao.search-url}") String searchUrl,
                       @Value("${api.kakao.key}") String apiKey,
                       RestTemplate restTemplate) {
        this.searchUrl = searchUrl;
        this.apiKey = apiKey;
        this.restTemplate = restTemplate;
    }

    @Override
    public BlogPageDto searchBlog(KaKaoSearchRequest searchDto) {
        if (StringUtils.isBlank(searchDto.getQuery())) {
            throw new IllegalArgumentException("keyword, url 값은 필수 입니다.");
        }

        var response = restTemplate.exchange(
                buildUri(searchDto),
                HttpMethod.GET,
                new HttpEntity<>(buildHeaders()),
                new ParameterizedTypeReference<KaKaoBlogResponse>() {
                }
        ).getBody();

        return KaKaoBlogToBlogConverter.convert(response, searchDto.getPage(), searchDto.getSize());
    }

    private String buildUri(KaKaoSearchRequest searchDto) {
        return UriComponentsBuilder.fromUriString(searchUrl)
                .queryParam("query", searchDto.getQuery())
                .queryParam("sort", searchDto.getSort())
                .queryParam("page", searchDto.getPage())
                .queryParam("size", searchDto.getSize())
                .build()
                .toUriString();
    }

    private HttpHeaders buildHeaders() {
        Map<String, String> headers = Maps.newHashMap();
        headers.put("Authorization", String.format("KakaoAK %s", apiKey));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);

        return httpHeaders;
    }
}
