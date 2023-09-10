package com.ssafy.tott.api.shinhan.service.searchname;

import com.ssafy.tott.api.core.FetchAPICore;
import com.ssafy.tott.api.core.dto.APIRequest;
import com.ssafy.tott.api.shinhan.dto.response.ShinhanBankAPIResponse;
import com.ssafy.tott.api.shinhan.factory.ShinhanBankWebClientFactory;
import com.ssafy.tott.api.shinhan.service.searchname.dto.request.ShinhanBankSearchNameRequest;
import com.ssafy.tott.api.shinhan.service.searchname.dto.response.ShinhanBankSearchNameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Component
public class ShinhanBankSearchNameFetchAPI implements FetchAPICore {
    private final ShinhanBankWebClientFactory shinhanBankWebClientFactory;

    @Value("${SHINHAN_BANK.API.URI.SEARCH_NAME}")
    private String uri;

    @Override
    public ShinhanBankSearchNameResponse fetchAPI(APIRequest json) {
        ShinhanBankSearchNameRequest shinhanBankSearchNameRequest = (ShinhanBankSearchNameRequest) json;
        WebClient webClient = shinhanBankWebClientFactory.createWebClientWithURI(uri);
        return webClient
                .post()
                .bodyValue(shinhanBankSearchNameRequest.getJson())
                .retrieve()
                .bodyToMono(ShinhanBankSearchNameResponse.class)
                .block();
    }
}
