package sample.wooni.blog.client.naver;

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
import sample.wooni.blog.service.output.naver.ExternalNaverBlogOutput;
import sample.wooni.blog.service.output.naver.dto.request.NaverSearchRequest;
import sample.wooni.blog.service.output.naver.dto.response.NaverBlogResponse;

import java.util.Map;

@Service
public class NaverClient implements ExternalNaverBlogOutput {
    private final String searchUrl;
    private final String clientId;
    private final String secretKey;
    private final RestTemplate restTemplate;

    public NaverClient(@Value("${api.naver.search-url}") String searchUrl,
                       @Value("${api.naver.client-id}") String clientId,
                       @Value("${api.naver.secret-key}") String secretKey,
                       RestTemplate restTemplate) {
        this.searchUrl = searchUrl;
        this.clientId = clientId;
        this.secretKey = secretKey;
        this.restTemplate = restTemplate;
    }

    @Override
    public NaverBlogResponse search(NaverSearchRequest request) {
        if (StringUtils.isBlank(request.query())) {
            throw new IllegalArgumentException("keyword, url 값은 필수 입니다.");
        }

        return restTemplate.exchange(
                buildUri(request),
                HttpMethod.GET,
                new HttpEntity<>(buildHeaders()),
                new ParameterizedTypeReference<NaverBlogResponse>() {
                }
        ).getBody();
    }

    private String buildUri(NaverSearchRequest searchDto) {
        return UriComponentsBuilder.fromUriString(searchUrl)
                .queryParam("query", searchDto.query())
                .queryParam("sort", searchDto.getSort())
                .queryParam("display", searchDto.getDisplay())
                .queryParam("start", searchDto.getStart())
                .build()
                .toUriString();
    }

    private HttpHeaders buildHeaders() {
        Map<String, String> headers = Maps.newHashMap();
        headers.put("X-Naver-Client-Id", clientId);
        headers.put("X-Naver-Client-Secret", secretKey);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);

        return httpHeaders;
    }
}
