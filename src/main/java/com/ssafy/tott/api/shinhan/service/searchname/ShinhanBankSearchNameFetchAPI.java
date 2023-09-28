package com.ssafy.tott.api.shinhan.service.searchname;

import com.ssafy.tott.api.core.FetchAPICore;
import com.ssafy.tott.api.core.dto.APIRequest;
import com.ssafy.tott.api.exception.APIErrorCode;
import com.ssafy.tott.api.exception.APIException;
import com.ssafy.tott.api.shinhan.dto.request.ShinhanBankAPIRequest;
import com.ssafy.tott.api.shinhan.factory.ShinhanBankWebClientFactory;
import com.ssafy.tott.api.shinhan.service.searchname.dto.request.ShinhanBankSearchNameRequest;
import com.ssafy.tott.api.shinhan.service.searchname.dto.request.ShinhanBankSearchNameRequestDataBody;
import com.ssafy.tott.api.shinhan.service.searchname.dto.response.ShinhanBankSearchNameResponse;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Component
public class ShinhanBankSearchNameFetchAPI implements FetchAPICore {
    private final ShinhanBankWebClientFactory shinhanBankWebClientFactory;

    @Value("${SHINHAN_BANK.API.URI.SEARCH_NAME}")
    private String uri;

    @Value("${SHINHAN_BANK.API.KEY}")
    private String validateKey; // 실제 `API`가 존재시 삭제 필요

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

    private ShinhanBankSearchNameResponse dummyFetchAPI(APIRequest request) {
        if (request instanceof ShinhanBankSearchNameRequest) {
            ShinhanBankSearchNameRequest shinhanBankSearchNameRequest = (ShinhanBankSearchNameRequest) request;
            ShinhanBankAPIRequest searchNameRequest = parseByJson(shinhanBankSearchNameRequest.getJson());
            validateApiKey(searchNameRequest.getDataHeader().getApikey());
        }
        throw new APIException(APIErrorCode.ERROR_SERVER_BY_JSON_PROCESSING);
    }

    private ShinhanBankAPIRequest parseByJson(String json) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(json);
            JSONObject dataHeader = (JSONObject) object.get("dataHeader");
            JSONObject dataBody = (JSONObject) object.get("dataBody");
            String apiKey = (String) dataHeader.get("apiKey");
            String bankCode = (String) dataBody.get("입금은행코드");
            String account = (String) dataBody.get("입금계좌번호");
            return ShinhanBankAPIRequest.of(apiKey, ShinhanBankSearchNameRequestDataBody.of(bankCode, account));
        } catch (ParseException e) {
            throw new APIException(APIErrorCode.ERROR_SERVER_BY_JSON_PROCESSING);
        }
    }

    private void validateApiKey(String apiKey) {
        if (!apiKey.equals(validateKey)) {
            throw new APIException(APIErrorCode.ERROR_SERVER_BY_API_KEY_IS_NOT_VALID);
        }
    }
}
