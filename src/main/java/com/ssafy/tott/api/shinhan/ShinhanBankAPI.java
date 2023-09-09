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
import com.ssafy.tott.api.shinhan.service.searchaccounts.dto.ShinhanBankSearchAccountsFetchAPI;
import com.ssafy.tott.api.shinhan.service.searchaccounts.dto.request.ShinhanBankSearchAccountsRequestBody;
import com.ssafy.tott.api.shinhan.service.searchname.ShinhanBankSearchNameFetchAPI;
import com.ssafy.tott.api.shinhan.service.searchname.dto.request.ShinhanBankSearchNameRequestDataBody;
import com.ssafy.tott.api.shinhan.service.transfer1.ShinhanBankTransfer1FetchAPI;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.request.ShinhanBankTransfer1RequestDataBody;
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

    @Value("${SHINHAN_BANK.API.KEY}")
    private String key;

    public ShinhanBankAPIResponse fetchSearchNameAPI(BankCode bankCode, String account) {
        ShinhanBankAPIRequest request = ShinhanBankAPIRequest.of(
                key,
                ShinhanBankSearchNameRequestDataBody.of(bankCode.getCode(), account)
        );
        String json = convertRequestToJson(request);
        ShinhanBankAPIResponse response = searchNameAPI.fetchAPI(json);
        validate(response);
        return response;
    }

    public ShinhanBankAPIResponse fetchTransfer1API(BankCode bankCode, String accountNumber, String memo) {
        ShinhanBankAPIRequest request = ShinhanBankAPIRequest.of(
                key,
                ShinhanBankTransfer1RequestDataBody.of(bankCode, accountNumber, memo)
        );
        logging(request.getShinhanBankDataBody());
        String json = convertRequestToJson(request);
        ShinhanBankAPIResponse response = transfer1API.fetchAPI(json);
        validate(response);
        return response;
    }

    public ShinhanBankAPIResponse fetchSearchAccountsAPI(String encodedName) {
        ShinhanBankAPIRequest request = ShinhanBankAPIRequest.of(
                key,
                ShinhanBankSearchAccountsRequestBody.from(encodedName)
        );
        logging(request.getShinhanBankDataBody());
        String json = convertRequestToJson(request);
        ShinhanBankAPIResponse response = searchAccountsAPI.fetchAPI(json);
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
        log.info("{} [{}] ---> {}",
                LocalDateTime.now(),
                Thread.currentThread().getStackTrace()[1].getClassName(),
                shinhanBankDataBody
        );
    }
}
