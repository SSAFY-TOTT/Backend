package com.ssafy.tott.api.kakao.module;

import com.ssafy.tott.api.kakao.data.Documents;
import com.ssafy.tott.api.kakao.data.KakaoAPIResponse;
import com.ssafy.tott.api.kakao.property.KakaoAddressProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@RequestMapping("/kakao")
@RestController
public class KakaoMapAPI {
    private final WebClient kakaoWebClient;
    private final KakaoAddressProperties kakaoAddressProperties;

    @GetMapping("/abc")
    public Documents kakaoAddressSearch(String sggName, String bjDongName, String bobn, String bubn) {
        /* roadAddress : 지번 ( 구 / 동 / 본번-부번 ) */
        StringBuilder sb = new StringBuilder()
                .append(sggName).append(" ")
                .append(bjDongName).append(" ")
                .append(bobn).append("-")
                .append(bubn);
        KakaoAPIResponse kakaoAPIResponse = kakaoWebClient.method(kakaoAddressProperties.getMethod())
                .uri(builder -> builder.path(kakaoAddressProperties.getPath())
                        .queryParam("query", sb.toString())
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(KakaoAPIResponse.class)
                .block();
        return kakaoAPIResponse.getDocuments().get(0);
    }
    // TODO: 2023/09/03 좌표 Parsing 필요
}