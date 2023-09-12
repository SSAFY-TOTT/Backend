package com.ssafy.tott.api.shinhan.service.searchaccounts;

import com.ssafy.tott.api.core.FetchAPICore;
import com.ssafy.tott.api.core.dto.APIRequest;
import com.ssafy.tott.api.shinhan.factory.ShinhanBankWebClientFactory;
import com.ssafy.tott.api.shinhan.service.searchaccounts.dto.request.ShinhanBankSearchAccountsRequest;
import com.ssafy.tott.api.shinhan.service.searchaccounts.dto.response.ShinhanBankSearchAccountsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Component
public class ShinhanBankSearchAccountsFetchAPI implements FetchAPICore {
    private final ShinhanBankWebClientFactory shinhanBankWebClientFactory;

    @Value("${SHINHAN_BANK.API.URI.SEARCH_ACCOUNTS}")
    private String uri;

    @Override
    public ShinhanBankSearchAccountsResponse fetchAPI(APIRequest request) {
        ShinhanBankSearchAccountsRequest shinhanBankSearchAccountsRequest =
                (ShinhanBankSearchAccountsRequest) request;
        WebClient webClient = shinhanBankWebClientFactory.createWebClientWithURI(uri);
        return webClient
                .post()
                .bodyValue(shinhanBankSearchAccountsRequest.getJson())
                .retrieve()
                .bodyToMono(ShinhanBankSearchAccountsResponse.class)
                .block();
    }
}
