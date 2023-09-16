package com.ssafy.tott.api.shinhan.service.searchcreditline;

import com.ssafy.tott.api.core.FetchAPICore;
import com.ssafy.tott.api.core.dto.APIRequest;
import com.ssafy.tott.api.exception.APIErrorCode;
import com.ssafy.tott.api.exception.APIException;
import com.ssafy.tott.api.shinhan.factory.ShinhanBankWebClientFactory;
import com.ssafy.tott.api.shinhan.service.searchcreditline.dto.request.ShinhanBankSearchCreditLineRequest;
import com.ssafy.tott.api.shinhan.service.searchcreditline.dto.response.ShinhanBankSearchCreditLineResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
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
        return fetchAPIWithRetry(
                webClient, shinhanBankSearchCreditLineRequest, 1, LocalDateTime.now().plusSeconds(2));
    }

    private ShinhanBankSearchCreditLineResponse fetchAPIWithRetry(
            WebClient webClient, ShinhanBankSearchCreditLineRequest request,
            int tryCount, LocalDateTime endTime) {
        try {
//            log.info("tryCount : [{}]", tryCount);
            return webClient
                    .post()
                    .bodyValue(request.getJson())
                    .retrieve()
                    .bodyToMono(ShinhanBankSearchCreditLineResponse.class)
                    .block();
        } catch (WebClientResponseException e) {
            if (canRetry(tryCount, endTime)) {
                return fetchAPIWithRetry(webClient, request, tryCount + 1, endTime);
            }
            throw new APIException(APIErrorCode.ERROR_SERVER_BY_OUTER_API_SERVER);
        }
    }

    private boolean canRetry(int tryCount, LocalDateTime endTime) {
        return tryCount <= 4 || LocalDateTime.now().isBefore(endTime);
    }
}
