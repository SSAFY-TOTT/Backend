package com.ssafy.tott.api.shinhan.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
public class ShinhanBankWebClientFactory {

    @Value("${SHINHAN_BANK.API.URL}")
    private String url;

    public WebClient createWebClientWithURI(String uri) {
        return WebClient.builder()
                .baseUrl(Objects.requireNonNull(url) + uri)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_ENCODING, StandardCharsets.UTF_8.name())
                .build();
    }
}
