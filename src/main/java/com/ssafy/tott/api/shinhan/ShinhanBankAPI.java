package com.ssafy.tott.api.shinhan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.tott.api.config.ObjectMapperConfig;
import com.ssafy.tott.api.shinhan.dto.request.ShinhanBankAPIRequest;
import com.ssafy.tott.api.shinhan.dto.response.ShinhanBankAPIResponse;
import com.ssafy.tott.api.shinhan.service.transfer1.ShinhanBankTransfer1API;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.request.Transfer1RequestDataBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ShinhanBankAPI {
    private final ObjectMapperConfig objectMapperConfig;
    private final ShinhanBankTransfer1API transfer1API;

    @Value("${SHINHAN_BANK.API.KEY}")
    private String key;

    public ShinhanBankAPIResponse getTransfer1API(String bankCode, String account, String memo) {
        ShinhanBankAPIRequest request = ShinhanBankAPIRequest.of(key, Transfer1RequestDataBody.of(bankCode, account, memo));
        String json = requestToJson(request);
        return transfer1API.fetchAPI(json);
    }

    private String requestToJson(ShinhanBankAPIRequest data) {
        ObjectMapper objectMapper = objectMapperConfig.getObjectMapper();
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
