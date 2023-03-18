package com.woonis.blogapi.controller;

import com.woonis.blogapi.service.kakao.KaKaoService;
import com.woonis.blogapi.service.kakao.dto.KaKaoBlogPageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class KaKaoController {

    private final KaKaoService service;

    public KaKaoController(KaKaoService service) {
        this.service = service;
    }

    @GetMapping("/api/v1/kakao/blog")
    public KaKaoBlogPageDto fetchKaKaoBlog() {
        return service.search();
    }
}
