package com.ssafy.tott.api.shinhan.service.transfer1;

import com.ssafy.tott.api.core.FetchAPICore;
import com.ssafy.tott.api.core.dto.APIRequest;
import com.ssafy.tott.api.shinhan.dto.response.ShinhanBankAPIResponse;
import com.ssafy.tott.api.shinhan.factory.ShinhanBankWebClientFactory;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.request.ShinhanBankTransfer1Request;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.response.ShinhanBankTransfer1Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Component
public class ShinhanBankTransfer1FetchAPI implements FetchAPICore {

    private final ShinhanBankWebClientFactory shinhanBankWebClientFactory;

    @Value("${SHINHAN_BANK.API.URI.1TRANSFER}")
    private String uri;

    @Override
    public ShinhanBankTransfer1Response fetchAPI(APIRequest request) {
        ShinhanBankTransfer1Request shinhanBankTransfer1Request = (ShinhanBankTransfer1Request) request;
        WebClient webClient = shinhanBankWebClientFactory.createWebClientWithURI(uri);
        return webClient
                .post()
                .bodyValue(shinhanBankTransfer1Request.getJson())
                .retrieve()
                .bodyToMono(ShinhanBankTransfer1Response.class)
                .block();
    }
}
