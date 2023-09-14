package com.ssafy.tott.api.shinhan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.tott.account.domain.BankCode;
import com.ssafy.tott.api.config.ObjectMapperConfig;
import com.ssafy.tott.api.exception.APIErrorCode;
import com.ssafy.tott.api.exception.APIException;
import com.ssafy.tott.api.shinhan.dto.ShinhanBankDataBody;
import com.ssafy.tott.api.shinhan.dto.request.ShinhanBankAPIRequest;
import com.ssafy.tott.api.shinhan.dto.response.ShinhanBankAPIResponse;
import com.ssafy.tott.api.shinhan.service.searchaccounts.ShinhanBankSearchAccountsFetchAPI;
import com.ssafy.tott.api.shinhan.service.searchaccounts.dto.request.ShinhanBankSearchAccountsRequest;
import com.ssafy.tott.api.shinhan.service.searchaccounts.dto.request.ShinhanBankSearchAccountsRequestBody;
import com.ssafy.tott.api.shinhan.service.searchaccounts.dto.response.ShinhanBankSearchAccountsResponse;
import com.ssafy.tott.api.shinhan.service.searchcreditline.ShinhanBankSearchCreditLineFetchAPI;
import com.ssafy.tott.api.shinhan.service.searchcreditline.dto.request.ShinhanBankSearchCreditLineRequest;
import com.ssafy.tott.api.shinhan.service.searchcreditline.dto.request.ShinhanBankSearchCreditLineRequestBody;
import com.ssafy.tott.api.shinhan.service.searchcreditline.dto.response.ShinhanBankSearchCreditLineResponse;
import com.ssafy.tott.api.shinhan.service.searchname.ShinhanBankSearchNameFetchAPI;
import com.ssafy.tott.api.shinhan.service.searchname.dto.request.ShinhanBankSearchNameRequest;
import com.ssafy.tott.api.shinhan.service.searchname.dto.request.ShinhanBankSearchNameRequestDataBody;
import com.ssafy.tott.api.shinhan.service.searchname.dto.response.ShinhanBankSearchNameResponse;
import com.ssafy.tott.api.shinhan.service.transfer1.ShinhanBankTransfer1FetchAPI;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.request.ShinhanBankTransfer1Request;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.request.ShinhanBankTransfer1RequestDataBody;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.response.ShinhanBankTransfer1Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
@Component
public class ShinhanBankAPI {
    private final ObjectMapperConfig objectMapperConfig;
    private final ShinhanBankTransfer1FetchAPI transfer1API;
    private final ShinhanBankSearchNameFetchAPI searchNameAPI;
    private final ShinhanBankSearchAccountsFetchAPI searchAccountsAPI;

    private final ShinhanBankSearchCreditLineFetchAPI searchCreditLineFetchAPI;

    @Value("${SHINHAN_BANK.API.KEY}")
    private String key;

    @Value("${SHINHAN_BANK.API.SERVICE_KEY.SEARCH_CREDIT_LINE}")
    private String serviceKey;

    public ShinhanBankSearchNameResponse fetchSearchNameAPI(BankCode bankCode, String account) {
        ShinhanBankAPIRequest request = ShinhanBankAPIRequest.of(
                key, ShinhanBankSearchNameRequestDataBody.of(bankCode.getCode(), account));

        ShinhanBankSearchNameResponse response =
                searchNameAPI.fetchAPI(ShinhanBankSearchNameRequest.toRequest(convertRequestToJson(request)));

        validate(response);
        return response;
    }

    public ShinhanBankTransfer1Response fetchTransfer1API(BankCode bankCode, String accountNumber, String memo) {
        ShinhanBankAPIRequest request = ShinhanBankAPIRequest.of(
                key, ShinhanBankTransfer1RequestDataBody.of(bankCode, accountNumber, memo));

        logging(request.getShinhanBankDataBody());

        ShinhanBankTransfer1Response response =
                transfer1API.fetchAPI(ShinhanBankTransfer1Request.toRequest(convertRequestToJson(request)));

        validate(response);
        return response;
    }

    public ShinhanBankSearchAccountsResponse fetchSearchAccountsAPI(String encodedName) {
        ShinhanBankAPIRequest request =
                ShinhanBankAPIRequest.of(key, ShinhanBankSearchAccountsRequestBody.from(encodedName));

        logging(request.getShinhanBankDataBody());

        ShinhanBankSearchAccountsResponse response = searchAccountsAPI.fetchAPI(
                ShinhanBankSearchAccountsRequest.toRequest(convertRequestToJson(request)));
        response.initAccounts();    // 랜덤한 계좌번호를 조회하기 위한 숫자
        validate(response);
        return response;
    }

    public ShinhanBankSearchCreditLineResponse fetchSearchCreditLineAPI(
            String linkedTransactionInformation, String housingLocationCode, String rentGtn, String annualIncome
    ) {
        ShinhanBankAPIRequest request = ShinhanBankAPIRequest.of(
                key, ShinhanBankSearchCreditLineRequestBody.of(
                        serviceKey, linkedTransactionInformation, housingLocationCode, rentGtn, annualIncome));

        logging(request.getShinhanBankDataBody());

        ShinhanBankSearchCreditLineResponse response = searchCreditLineFetchAPI.fetchAPI(
                ShinhanBankSearchCreditLineRequest.toRequest(convertRequestToJson(request)));

        validate(response);
        return response;
    }

    private String convertRequestToJson(ShinhanBankAPIRequest data) {
        ObjectMapper objectMapper = objectMapperConfig.getObjectMapper();
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new APIException(APIErrorCode.ERROR_SERVER_BY_JSON_PROCESSING);
        }
    }

    private void validate(ShinhanBankAPIResponse response) {
        if (response.isFailed()) {
            throw new APIException(APIErrorCode.ERROR_SERVER_BY_OUTER_API_SERVER);
        }
    }

    /* TODO: 2023/09/04 추후 `AOP`로 분리 */
    private void logging(ShinhanBankDataBody shinhanBankDataBody) {
        log.info(
                "{} [{}] ---> {}",
                LocalDateTime.now(),
                Thread.currentThread().getStackTrace()[1].getClassName(),
                shinhanBankDataBody);
    }
}
