package com.ssafy.tott.api.shinhan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.tott.account.domain.BankCode;
import com.ssafy.tott.api.config.ObjectMapperConfig;
import com.ssafy.tott.api.exception.APIErrorCode;
import com.ssafy.tott.api.exception.APIException;
import com.ssafy.tott.api.shinhan.dto.DataBody;
import com.ssafy.tott.api.shinhan.dto.request.ShinhanBankAPIRequest;
import com.ssafy.tott.api.shinhan.dto.response.ShinhanBankAPIResponse;
import com.ssafy.tott.api.shinhan.service.searchname.ShinhanBankSearchNameAPI;
import com.ssafy.tott.api.shinhan.service.searchname.dto.request.SearchNameRequestDataBody;
import com.ssafy.tott.api.shinhan.service.transfer1.ShinhanBankTransfer1API;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.request.Transfer1RequestDataBody;
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
    private final ShinhanBankTransfer1API transfer1API;
    private final ShinhanBankSearchNameAPI searchNameAPI;

    @Value("${SHINHAN_BANK.API.KEY}")
    private String key;

    public ShinhanBankAPIResponse fetchSearchNameAPI(String bankCode, String account) {
        ShinhanBankAPIRequest request = ShinhanBankAPIRequest.of(
                key,
                SearchNameRequestDataBody.of(bankCode, account)
        );
        logging(request.getDataBody());
        String json = convertRequestToJson(request);
        return searchNameAPI.fetchAPI(json);
    }

    public ShinhanBankAPIResponse fetchTransfer1API(BankCode bankCode, String accountNumber, String memo) {
        ShinhanBankAPIRequest request = ShinhanBankAPIRequest.of(
                key,
                Transfer1RequestDataBody.of(bankCode, accountNumber, memo)
        );
        String json = convertRequestToJson(request);
        logging(request.getDataBody());
        return transfer1API.fetchAPI(json);
    }

    private void logging(DataBody dataBody) {
        log.info("{} [{}] ---> {}",
                LocalDateTime.now(),
                Thread.currentThread().getStackTrace()[1].getClassName(),
                dataBody
        );
    }

    private String convertRequestToJson(ShinhanBankAPIRequest data) {
        ObjectMapper objectMapper = objectMapperConfig.getObjectMapper();
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new APIException(APIErrorCode.SERVER_ERROR_BY_JSON_PROCESSING);
        }
    }
}
