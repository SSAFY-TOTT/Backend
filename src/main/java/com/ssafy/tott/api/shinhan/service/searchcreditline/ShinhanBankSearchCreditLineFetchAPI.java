package com.ssafy.tott.api.shinhan.service.searchcreditline;

import com.ssafy.tott.api.core.FetchAPICore;
import com.ssafy.tott.api.core.dto.APIRequest;
import com.ssafy.tott.api.shinhan.factory.ShinhanBankWebClientFactory;
import com.ssafy.tott.api.shinhan.service.searchcreditline.dto.request.ShinhanBankSearchCreditLineRequest;
import com.ssafy.tott.api.shinhan.service.searchcreditline.dto.response.ShinhanBankSearchCreditLineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Component
public class ShinhanBankSearchCreditLineFetchAPI implements FetchAPICore {
    private final ShinhanBankWebClientFactory shinhanBankWebClientFactory;

    @Value("${SHINHAN_BANK.API.URI.SEARCH_CREDIT_LINE}")
    private String uri;


    @Override
    public ShinhanBankSearchCreditLineResponse fetchAPI(APIRequest request) {
        ShinhanBankSearchCreditLineRequest shinhanBankSearchCreditLineRequest =
                (ShinhanBankSearchCreditLineRequest) request;
        WebClient webClient = shinhanBankWebClientFactory.createWebClientWithURI(uri);
        return webClient
                .post()
                .bodyValue(shinhanBankSearchCreditLineRequest.getJson())
                .retrieve()
                .bodyToMono(ShinhanBankSearchCreditLineResponse.class)
                .block();
    }
}
