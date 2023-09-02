package com.ssafy.tott.api.kakao.module;

import com.ssafy.tott.api.kakao.data.KakaoAPIResponse;
import com.ssafy.tott.api.kakao.property.KakaoAddressProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class KakaoMapAPI {
    private final WebClient kakaoWebClient;
    private final KakaoAddressProperties kakaoAddressProperties;

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