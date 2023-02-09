package com.book.boogit.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
public class kakaoController {

    @Value("${kakao.key}")
    private String key;

    @GetMapping("/kakao")
    public Map callBookSearchApi(@RequestParam String query) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + key);              // 권한 설정
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);   // 엔티티로 변환
        String url = "https://dapi.kakao.com/v3/search/book";
        URI targetUrl = UriComponentsBuilder
                .fromUriString(url)                                      // 호출 url
                .queryParam("query", query)                        // 파라미터
                .queryParam("size", 3)
                .build()
                .encode(StandardCharsets.UTF_8)                          // 인코딩
                .toUri();

        ResponseEntity<Map> result = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, Map.class);

        return result.getBody();

    }



}
