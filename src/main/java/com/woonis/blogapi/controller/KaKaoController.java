package com.woonis.blogapi.controller;

import com.woonis.blogapi.service.kakao.KaKaoService;
import com.woonis.blogapi.service.kakao.dto.KaKaoBlogPageDto;
import com.woonis.blogapi.service.kakao.dto.KaKaoBlogSearchDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;

@Slf4j
@RestController
public class KaKaoController {

    private final KaKaoService service;

    public KaKaoController(KaKaoService service) {
        this.service = service;
    }

    @GetMapping("/api/v1/kakao/blog")
    public KaKaoBlogPageDto fetchKaKaoBlog(
            @Valid KaKaoBlogSearchDto request,
            @Valid @Max(value = 50) @RequestParam(required = false, defaultValue = "1") int page,
            @Valid @Max(value = 50) @RequestParam(required = false, defaultValue = "10") int countPerPage
    ) {
        return service.search(request, page, countPerPage);
    }
}
