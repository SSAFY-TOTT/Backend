package com.ssafy.tott.kakao.module;

import com.ssafy.tott.kakao.data.KakaoAPIResponse;
import com.ssafy.tott.kakao.property.KakaoAddressProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@RestController
@RequestMapping("/kakao")
public class KakaoMapAPI {
    private final WebClient kakaoWebClient;
    private final KakaoAddressProperties kakaoAddressProperties;

    @GetMapping("/address")
    public KakaoAPIResponse kakaoAddressSearch(String roadAddress) {
        /* roadAddress : 지번 ( 구 / 동 / 본번-부번 ) */
        return kakaoWebClient.method(kakaoAddressProperties.getMethod())
                .uri(builder -> builder.path(kakaoAddressProperties.getPath())
                        .queryParam("query", roadAddress)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(KakaoAPIResponse.class)
                .block();
    }
    // TODO: 2023/09/03 좌표 Parsing 필요
}