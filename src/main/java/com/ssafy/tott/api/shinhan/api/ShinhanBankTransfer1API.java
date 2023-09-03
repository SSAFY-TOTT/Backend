package com.ssafy.tott.api.shinhan.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.tott.api.CoreAPI;
import com.ssafy.tott.api.shinhan.api.body.Transfer1DataBody;
import com.ssafy.tott.api.shinhan.infra.ShinhanWebClientFactory;
import com.ssafy.tott.api.shinhan.request.ShinhanAPIRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@RequiredArgsConstructor
@Component
public class ShinhanBankTransfer1API implements CoreAPI {

    private final ShinhanWebClientFactory shinhanWebClientFactory;

    @Value("${SHINHAN_BANK.API.KEY}")
    private String key;

    @Value("${SHINHAN_BANK.API.URI.1TRANSFER}")
    private String uri;

    @Override
    public String fetchAPI() {
        WebClient webClient = shinhanWebClientFactory.createWebClient(uri);
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(ShinhanAPIRequest.of(
                    key,
                    Transfer1DataBody.of("088", "110222999999", "1234 SSAFY"))
            );
        } catch (JsonProcessingException e) {
            return null;
        }
        return webClient.post()
                .bodyValue(json)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
