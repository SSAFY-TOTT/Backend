package com.ssafy.tott.api.shinhan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.tott.api.config.ObjectMapperConfig;
import com.ssafy.tott.api.exception.APIErrorCode;
import com.ssafy.tott.api.exception.APIException;
import com.ssafy.tott.api.shinhan.dto.request.ShinhanBankAPIRequest;
import com.ssafy.tott.api.shinhan.dto.response.ShinhanBankAPIResponse;
import com.ssafy.tott.api.shinhan.service.searchname.ShinhanBankSearchNameAPI;
import com.ssafy.tott.api.shinhan.service.searchname.dto.request.SearchNameRequestDataBody;
import com.ssafy.tott.api.shinhan.service.transfer1.ShinhanBankTransfer1API;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.request.Transfer1RequestDataBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ShinhanBankAPI {
    private final ObjectMapperConfig objectMapperConfig;
    private final ShinhanBankTransfer1API transfer1API;
    private final ShinhanBankSearchNameAPI searchNameAPI;

    @Value("${SHINHAN_BANK.API.KEY}")
    private String key;

    public ShinhanBankAPIResponse getSearchNameAPI(String bankCode, String account) {
        ShinhanBankAPIRequest request = ShinhanBankAPIRequest.of(
                key,
                SearchNameRequestDataBody.of(bankCode, account)
        );
        String json = getJson(request);
        return searchNameAPI.fetchAPI(json);
    }

    public ShinhanBankAPIResponse getTransfer1API(String bankCode, String account, String memo) {
        ShinhanBankAPIRequest request = ShinhanBankAPIRequest.of(
                key,
                Transfer1RequestDataBody.of(bankCode, account, memo)
        );
        String json = getJson(request);
        return transfer1API.fetchAPI(json);
    }

    private String getJson(ShinhanBankAPIRequest request) {
        return requestToJson(request)
                .orElseThrow(() -> new APIException(APIErrorCode.SERVER_ERROR_JSON_PROCESS));
    }

    private Optional<String> requestToJson(ShinhanBankAPIRequest data) {
        ObjectMapper objectMapper = objectMapperConfig.getObjectMapper();
        try {
            return Optional.of(objectMapper.writeValueAsString(data));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }
}
