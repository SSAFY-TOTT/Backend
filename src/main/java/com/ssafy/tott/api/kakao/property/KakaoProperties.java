package com.ssafy.tott.api.kakao.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
public class KakaoProperties {
    @Value("${kakao.API.URL}")
    private String restApiKey;
    private final String baseUrl = "http://dapi.kakao.com";

    public String getDefaultHeader() {
        return "KakaoAK " + restApiKey;
    }
}