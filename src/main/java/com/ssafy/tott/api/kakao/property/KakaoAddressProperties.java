package com.ssafy.tott.api.kakao.property;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
@Getter
public class KakaoAddressProperties {
    private final HttpMethod method = HttpMethod.GET;
    private final String path = "v2/local/search/address.json";
}
