package com.ssafy.tott.api.shinhan.service.searchname;

import com.ssafy.tott.api.APICore;
import com.ssafy.tott.api.shinhan.dto.response.ShinhanBankAPIResponse;
import com.ssafy.tott.api.shinhan.factory.ShinhanBankWebClientFactory;
import com.ssafy.tott.api.shinhan.service.searchname.dto.response.ShinhanBankSearchNameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Component
public class ShinhanBankSearchNameAPI implements APICore {
    private final ShinhanBankWebClientFactory shinhanBankWebClientFactory;

    @Value("${SHINHAN_BANK.API.URI.SEARCH_NAME}")
    private String uri;

    @Override
    public ShinhanBankAPIResponse fetchAPI(String json) {
        WebClient webClient = shinhanBankWebClientFactory.createWebClientWithURI(uri);
        return webClient.post()
                .bodyValue(json)
                .retrieve()
                .bodyToMono(ShinhanBankSearchNameResponse.class)
                .block();
    }
}
