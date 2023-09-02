package com.ssafy.tott.scheduler.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class KakaoProperties {
    @Value("${kakao.API.URL}")
    private String restApiKey;
    private final String baseUrl = "http://dapi.kakao.com";
    private final String defaultHeader = "KakaoAK " + restApiKey;
}
