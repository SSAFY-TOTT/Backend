package com.ssafy.tott.api.kakao.util;

import com.ssafy.tott.api.kakao.property.KakaoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class KakaoApiConfiguration {
    private final KakaoProperties kakaoProperties;

    public WebClient kakaoWebClient() {
        return WebClient.builder()
                .baseUrl(kakaoProperties.getBaseUrl())
                .defaultHeader("Authorization", kakaoProperties.getDefaultHeader())
                .build();
    }
}