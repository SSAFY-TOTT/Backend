package com.ssafy.tott.api.kakao.util;

import com.ssafy.tott.api.kakao.property.KakaoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class KakaoApiConfiguration {
  private final KakaoProperties kakaoProperties;

  @Bean
  public WebClient kakaoWebClient() {
    return WebClient.builder()
        .baseUrl(kakaoProperties.getBaseUrl())
        .defaultHeader("Authorization", kakaoProperties.getDefaultHeader())
        .build();
  }
}
